(ns handler
  (:require [clojure.tools.namespace.repl :refer [refresh]]
            [clj-http.client :as http]
            [clj-time.local :as localtime]
            [clj-time.core :as time]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [hiccup.core :refer [html]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.server.standalone]))

;;
;; Accessing the API
;;

(def TRAIN_API "https://rata.digitraffic.fi/api/v1/live-trains")

(defn get-trains [station]
  (:body
   (http/get TRAIN_API {:as :json :query-params {:station station}})))

;;
;; Time manipulation
;;

(defn show-time [t]
  (localtime/format-local-time t :hour-minute))

(defn in-the-future? [t]
  (time/before? (localtime/local-now) (localtime/to-local-date-time t)))

;;
;; Generating HTML
;;

(defn timetable [row]
  (let [scheduled (:scheduledTime row)
        actual (:actualTime row)]
    [:li
     (show-time scheduled)
     (when actual
       (list
        "->"
        (show-time actual)))
     (when (in-the-future? scheduled)
       "***")
     " "
     (:type row)
     " "
     (:stationShortCode row)]))

(defn train [t]
  [:li
   (:trainNumber t)
   " "
   (:trainType t)
   " "
   (:trainCategory t)
   " "
   (:commuterLineID t)
   [:ul
    (for [row (take 5 (:timeTableRows t))]
      (timetable row))]])

(defn render [trains]
  [:ul
   (for [t trains]
     (train t))])

(defn page []
  [:html
   [:head
    [:link {:rel "stylesheet" :href "/style.css" :type "text/css"}]]
   [:body
    [:h1 "Trains"]
    (render (get-trains "HKI"))]])

(defn hello [name]
  [:html
   [:body
    [:p
     "Hello "
     [:em name]
     ", good to see you!"]]])

;;
;; HTTP server
;;

(defroutes app-routes
  (GET "/" [] (html (page)))
  (GET "/hello/:name" [name] (html (hello name)))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

;;
;; For interactive development
;;

(def server-atom (atom nil))
(defn run []
  (when @server-atom (.stop @server-atom))
  (reset! server-atom (ring.server.standalone/serve #'app {:join? false
                                                           :auto-refresh? false
                                                           :open-browser? false})))
