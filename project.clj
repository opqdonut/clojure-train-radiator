(defproject clojure-train-radiator "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://github.com/opqdonut/clojure-train-radiator"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.clojure/tools.namespace "1.1.0"]
                 [cheshire "5.10.1"]
                 [clj-http "3.12.3"]
                 [clj-time "0.15.2"]
                 [compojure "1.6.2"]
                 [hiccup "1.0.5"]
                 [ring/ring-defaults "0.3.3"]
                 [ring-server "0.5.0"]
                 [javax.servlet/servlet-api "2.5"]
                 [ring/ring-mock "0.4.0"]]
  :plugins [[lein-ring "0.12.5"]
            [lein-ancient "1.0.0-RC3"]
            [nightlight/lein-nightlight "RELEASE"]]
  :ring {:handler handler/app}
  :main handler
  :repl-options {:init-ns handler})
