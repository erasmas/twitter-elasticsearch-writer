(ns twitter-elasticsearch-writer.camel
  (:use [camelarius.core])
  (:import (twitter4j Status)))

(defn extract-body
  [ex class]
  (.. ex (getIn) (getBody class)))

(def tweet-processor
  (processor
    (let [tweet (extract-body ex Status)
          body {"id" (.getId tweet)
                "name" (.. tweet (getUser) (getName))
                "followers_count" (.. tweet (getUser) (getFollowersCount))
                "content" (.getText tweet)
                "created_at" (.. tweet (getCreatedAt) (getTime))
                "language" (.getLang tweet)
                "retweet_count" (.getRetweetCount tweet)
                "timestamp" (System/currentTimeMillis)
                "location" (.. tweet (getUser) (getLocation))
                }]
      (set-body ex body))))

(defn create-route
  [twitter-uri elasticsearch-uri]
  (defroute (make-context)
            (from twitter-uri)
            (process tweet-processor)
            (to "log:tweet")
            (to elasticsearch-uri)))