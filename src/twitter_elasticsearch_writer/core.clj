(ns twitter-elasticsearch-writer.core
  (:require [twitter-elasticsearch-writer.camel :refer [create-route]]
            [clojure.tools.cli :refer [parse-opts]]
            [clojure.string :as str])
  (:gen-class))

(def cli-options
  [[nil "--twitter TWITER" "Camel Twitter endpoint URI"]
   [nil "--elasticsearch ELASTIC" "Camel ElasticSearch endpoint URI"]
   ["-h" "--help"]])

(def required-opts #{:twitter :elasticsearch})

(defn missing-required?
  "Returns true if opts is missing any of the required-opts"
  [opts]
  (not-every? opts required-opts))

(defn usage [options-summary]
  (->> ["twitter-elasticsearch-writer - reads from Twitter and writes to ElasticSearch."
        ""
        "Usage: java -jar twitter-elasticsearch-writer.jar --twiter TWITTER_URI --elasticsearch ES_URI"
        ""
        "Options:"
        options-summary
        ""
        "Example:"
        "java -jar twitter-elasticsearch-writer.jar \\"
        "          --twiter twitter://search?keywords=camel&consumerKey=[s]&consumerSecret=[s]&accessToken=[s]&accessTokenSecret=[s] \\"
        "          --elasticsearch elasticsearch://local?operation=INDEX&indexName=twitter&indexType=tweet"
        ""
        "Please refer to the Apache Camel documentation on how to configure Twitter and ElasticSearch URI's."
        ""
        "https://camel.apache.org/twitter"
        "https://camel.apache.org/elasticsearch.html"
        ""]
       (str/join \newline)))

(defn exit [status msg]
  (println msg)
  (System/exit status))

(defn error-msg [errors]
  (str "The following errors occurred while parsing your command:\n\n"
       (str/join \newline errors)))

(defn -main [& args]
  (let [{:keys [options errors summary]} (parse-opts args cli-options)]
    ;; Handle help and error conditions
    (cond
      (:help options) (exit 0 (usage summary))
      (missing-required? options) (exit 1 (usage summary))
      errors (exit 1 (error-msg errors)))
    ;; Start Camel route
    (create-route (:twitter options) (:elasticsearch options))
    ))
