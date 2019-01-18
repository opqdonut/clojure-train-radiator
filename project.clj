(defproject clojure-train-radiator "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://github.com/opqdonut/clojure-train-radiator"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [cheshire "5.8.1"]
                 [clj-http "3.9.1"]
                 [clj-time "0.15.1"]
                 [compojure "1.6.1"]
                 [hiccup "1.0.5"]
                 [ring/ring-defaults "0.3.2"]
                 [ring-server "0.5.0"]
                 [javax.servlet/servlet-api "2.5"]
                 [ring/ring-mock "0.3.2"]]
  :plugins [[lein-ring "0.9.7"]
            [nightlight/lein-nightlight "RELEASE"]]
  :ring {:handler handler/app}
  :main handler
  :repl-options {:init-ns handler})
