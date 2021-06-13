(ns clovaluator.comparators.operators
  (:require [clojure.set :as cljset]))

(defn equals
  [incoming-value config-value]
  (= incoming-value config-value))

(defn not-equals
  [incoming-value config-value]
  (not (= incoming-value config-value)))

(defn less-than
  [incoming-value config-value]
  (< incoming-value config-value))

(defn less-than-or-equal-to
  [incoming-value config-value]
  (<= incoming-value config-value))

(defn greater-than
  [incoming-value config-value]
  (> incoming-value config-value))

(defn greater-than-or-equal-to
  [incoming-value config-value]
  (>= incoming-value config-value))

(defn superset-of
  [incoming-value config-value]
  (cljset/superset? (set incoming-value) (set config-value)))

(defn not-superset-of
  [incoming-value config-value]
  (not (superset-of incoming-value config-value)))

(defn any-of
  [incoming-value config-value]
  (some? (some #(= % incoming-value) config-value)))

(defn none-of
  [incoming-value config-value]
  (not (any-of incoming-value config-value)))

(defn between
  [incoming-value config-value]
  (and (greater-than-or-equal-to incoming-value (first config-value))
       (less-than-or-equal-to incoming-value (second config-value))))

(defn not-between
  [incoming-value config-value]
  (not (and (greater-than-or-equal-to incoming-value (first config-value))
            (less-than-or-equal-to incoming-value (second config-value)))))

(defn intersection
  [incoming-value config-value]
  (not (empty? (cljset/intersection (set incoming-value) (set config-value)))))

(defn not-intersection
  [incoming-value config-value]
  (not (intersection incoming-value config-value)))

(defn regex-match
  [incoming-value config-value]
  (let [config-pattern (re-pattern config-value)]
    (some? (re-find config-pattern incoming-value))))

(defn regex-not-match
  [incoming-value config-value]
  (not (regex-match incoming-value config-value)))
