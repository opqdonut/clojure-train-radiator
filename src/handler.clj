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

(defn page []
  [:html
   [:body
    [:h1 "Trains"]
    [:ul
     [:li (str (count (get-trains)) " trains")]]]])

(defroutes app-routes
  (GET "/" [] (html (page)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
