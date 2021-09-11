(ns clovaluator.logger.log
  (:require [clojure.data.json :as json]
            [clojure.tools.logging :as log]))

(defn- to-json
  [log]
  (json/write-str log))

(defn- wrangle-stacktrace
  [exception]
  (Throwable->map exception))

(defn info
  ([namespace function message]
   (info namespace function message nil))
  ([namespace function message interface]
   (log/info (format "namespace: %s, function: %s, message: %s, interface: %s" (.toString namespace) function message (to-json interface)))))

(defn debug
  ([namespace function message]
   (debug namespace function message nil))
  ([namespace function message interface]
   (log/debug (format "namespace: %s, function: %s, message: %s, interface: %s" (.toString namespace) function message (to-json interface)))))

(defn warn
  ([namespace function message]
   (log/warn (format "namespace: %s, function: %s, message: %s" (.toString namespace) function message)))
  ([namespace function message interface]
   (log/warn (format "namespace: %s, function: %s, message: %s, interface: %s" (.toString namespace) function message (to-json interface))))
  ([namespace function message interface exception]
   (log/warn (format "namespace: %s, function: %s, message: %s, interface: %s, stacktrace: %s"
                     (.toString namespace) function message (to-json interface) (to-json (wrangle-stacktrace exception))))
   (log/error (format "namespace: %s, function: %s, message: %s, interface: %s, ex-message: %s, stacktrace: %s"
                      (.toString namespace) function message (to-json interface) (.getMessage exception) (wrangle-stacktrace exception)))))

(defn error
  ([namespace function message]
   (log/error (format "namespace: %s, function: %s, message: %s" (.toString namespace) function message)))
  ([namespace function message interface]
   (log/error (format "namespace: %s, function: %s, message: %s, interface: %s" (.toString namespace) function message (to-json interface)))))

(defn exception
  [namespace function message interface exception]
  (log/error (format "namespace: %s, function: %s, message: %s, interface: %s, ex-message: %s, stacktrace: %s"
                     (.toString namespace) function message (to-json interface) (.getMessage exception) (wrangle-stacktrace exception))))
