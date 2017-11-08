(ns handler
  (:require [clj-http.client :as http]
            [clj-time.local :as localtime]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [hiccup.core :refer [html]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(def TRAIN_API "https://rata.digitraffic.fi/api/v1/live-trains")

(defn get-trains [station]
  (:body
   (http/get TRAIN_API {:as :json :query-params {:station station}})))

(defn show-time [t]
  (localtime/format-local-time t :hour-minute))

(defn timetable [row]
  (let [scheduled (:scheduledTime row)
        actual (:actualTime row)]
    [:li
     (show-time scheduled)
     (when actual
       (list
        "->"
        (show-time actual)))
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

(defroutes app-routes
  (GET "/" [] (html (page)))
  (GET "/hello/:name" [name] (html (hello name)))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
