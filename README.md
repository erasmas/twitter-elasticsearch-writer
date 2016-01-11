# twitter-elasticsearch-writer

Simple Clojure app that uses [Apache Camel](https://camel.apache.org) and [camelarius](https://github.com/xulfus/camelarius) to read tweets from Twitter and write them to ElasticSearch.

## Usage

Build with [Leiningen](https://github.com/technomancy/leiningen):
```
lein uberjar
```

```
twitter-elasticsearch-writer - reads from Twitter and writes to ElasticSearch.

Usage: java -jar twitter-elasticsearch-writer.jar --twiter TWITTER_URI --elasticsearch ES_URI

Options:
      --twitter TWITER         Camel Twitter endpoint URI
      --elasticsearch ELASTIC  Camel ElasticSearch endpoint URI
  -h, --help

Example:
java -jar twitter-elasticsearch-writer.jar \
          --twiter twitter://search?keywords=camel&consumerKey=[s]&consumerSecret=[s]&accessToken=[s]&accessTokenSecret=[s] \
          --elasticsearch elasticsearch://local?operation=INDEX&indexName=twitter&indexType=tweet

Please refer to the Apache Camel documentation on how to configure Twitter and ElasticSearch URI's.

https://camel.apache.org/twitter
https://camel.apache.org/elasticsearch.html
```

## License

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
