(defproject twitter-elasticsearch-writer "0.1.0-SNAPSHOT"
  :description "Camel route that reads Twitter sample stream and writes it to ElasticSearch"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.cli "0.3.1"]
                 ; Camel
                 [camelarius "0.4.1"]
                 [org.apache.camel/camel-core "2.17.1"]
                 [org.apache.camel/camel-twitter "2.17.1"]
                 [org.apache.camel/camel-elasticsearch "2.17.1"]
                 ; Logging
                 [org.slf4j/slf4j-api "1.7.12"]
                 [org.slf4j/slf4j-log4j12 "1.7.12"]
                 ]
  :main twitter-elasticsearch-writer.core
  :aot [twitter-elasticsearch-writer.core]
  )
