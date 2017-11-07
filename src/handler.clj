(ns handler
  (:require [cheshire.core :as json]
            [clj-http.client :as http]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [hiccup.core :refer [html]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(def +train-api+ "https://rata.digitraffic.fi/api/v1/live-trains?station=HKI")

(defn get-trains []
  (json/parse-string (:body (http/get +train-api+))))

(defn timetable [row]
  [:li
   (get row "type")
   " "
   (get row "stationShortCode")
   " "
   (get row "scheduledTime")
   " "
   (get row "actualTime")
   " "
   (get row "differenceInMinutes")])

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
