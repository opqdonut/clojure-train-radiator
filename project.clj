(defproject clojure-train-radiator "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://github.com/opqdonut/clojure-train-radiator"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [cheshire "5.8.0"]
                 [clj-http "3.7.0"]
                 [compojure "1.5.1"]
                 [hiccup "1.0.5"]
                 [ring/ring-defaults "0.2.1"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})