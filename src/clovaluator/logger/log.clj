(ns clovaluator.logger.log
  (:require [cheshire.core :as cheshire]
            [clojure.tools.logging :as log]))

(defn- get-env []
  (System/getenv "ENVIRONMENT"))

(defn- convert-to-json
  [log-event]
  (cheshire/generate-string log-event))

(defn info
  [namespace function message interface]
  (log/debug (convert-to-json {:namespace (.toString namespace) :function function
                               :message   message :interface interface})))

(defn debug
  [namespace function message interface]
  (log/debug (convert-to-json {:namespace (.toString namespace) :function function
                               :message   message :interface interface})))

(defn error
  [namespace function message interface]
  (log/error (convert-to-json {:namespace (.toString namespace) :function function
                               :message   message :interface interface})))

(defn exception
  [namespace function message exception]
  (if (= "test" (get-env))
    (error (.toString namespace) function message {:exception (.getMessage exception)})
    (error (.toString namespace) function message {:exception (.getMessage exception) :stacktrace exception})))
