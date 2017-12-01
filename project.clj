(defproject clojure-train-radiator "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://github.com/opqdonut/clojure-train-radiator"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [cheshire "5.8.0"]
                 [clj-http "3.7.0"]
                 [clj-time "0.14.0"]
                 [compojure "1.5.1"]
                 [hiccup "1.0.5"]
                 [ring/ring-defaults "0.2.1"]
                 [ring-server "0.5.0"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler handler/app}
  :repl-options {:init-ns handler}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
