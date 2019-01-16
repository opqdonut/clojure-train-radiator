(ns handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [handler :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (.contains (:body response) "<h1>Trains</h1>"))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))

(deftest test-hello
  (let [response (app (mock/request :get "/hello/foobar"))]
    (is (.contains (:body response) "foobar"))))
