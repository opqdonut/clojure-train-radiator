(ns handler
  (:require [cheshire.core :as json]
            [clj-http.client :as http]
            [clj-time.local :as localtime]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [hiccup.core :refer [html]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(def +train-api+ "https://rata.digitraffic.fi/api/v1/live-trains?station=HKI")

(defn get-trains []
  (json/parse-string (:body (http/get +train-api+))))

(defn show-time [t]
  (localtime/format-local-time t :hour-minute))

(defn timetable [row]
  (let [scheduled (get row "scheduledTime")
        actual (get row "actualTime")]
    [:li
     (show-time scheduled)
     (when actual
       (list
        "->"
        (show-time (get row "actualTime"))))
     " "
     (get row "type")
     " "
     (get row "stationShortCode")]))

(defn train [t]
  [:li
   (get t "trainNumber")
   " "
   (get t "trainType")
   " "
   (get t "trainCategory")
   " "
   (get t "commuterLineID")
   [:ul
    (for [row (take 5 (get t "timeTableRows"))]
      (timetable row))]])

(defn render [trains]
  [:ul
   (for [t trains]
     (train t))])

(defn page []
  [:html
   [:body
    [:h1 "Trains"]
    (render (get-trains))]])

(defroutes app-routes
  (GET "/" [] (html (page)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
