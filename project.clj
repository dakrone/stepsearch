(defproject stepsearch "0.1.0-SNAPSHOT"
  :description "Dump ztext bible formatted "
  :url "http://example.com/FIXME"
  :repositories [["crosswire" "https://crosswire.org/mvn/content/groups/public/"]]
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/data.zip "0.1.3"]
                 [org.crosswire/jsword "2.1-SNAPSHOT"]
                 [cheshire "5.9.0"]
                 [clj-http "3.10.0"]]
  :resource-paths ["resources"]
  :main stepsearch.core)
