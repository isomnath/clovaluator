(ns clovaluator.comparators.operators
  (:require [clojure.set :as cljset]
            [clovaluator.logger.log :as log]))

(def ^:private current-namespace *ns*)

(defn- default-numeric-value
  []
  (log/warn current-namespace "process-numeric-value" "incoming value is nil defaulting to zero")
  0)

(defn- process-numeric-value
  [value]
  (if (not (nil? value))
    value
    (default-numeric-value)))

(defn equals
  [incoming-value config-value]
  (= incoming-value config-value))

(defn not-equals
  [incoming-value config-value]
  (not (= incoming-value config-value)))

(defn less-than
  [incoming-value config-value]
  (< (process-numeric-value incoming-value) config-value))

(defn less-than-any
  [incoming-value config-value]
  (some? (some #(less-than incoming-value %) (set config-value))))

(defn less-than-none
  [incoming-value config-value]
  (not (less-than-any incoming-value config-value)))

(defn less-than-every
  [incoming-value config-value]
  (every? #(less-than incoming-value %) config-value))

(defn any-less-than
  [incoming-value config-value]
  (some? (some #(less-than % config-value) incoming-value)))

(defn none-less-than
  [incoming-value config-value]
  (not (any-less-than incoming-value config-value)))

(defn every-less-than
  [incoming-value config-value]
  (every? #(less-than % config-value) incoming-value))

(defn less-than-or-equal-to
  [incoming-value config-value]
  (<= (process-numeric-value incoming-value) config-value))

(defn less-than-or-equal-to-any
  [incoming-value config-value]
  (some? (some #(less-than-or-equal-to incoming-value %) config-value)))

(defn less-than-or-equal-to-none
  [incoming-value config-value]
  (not (less-than-or-equal-to-any incoming-value config-value)))

(defn less-than-or-equal-to-every
  [incoming-value config-value]
  (every? #(less-than-or-equal-to incoming-value %) config-value))

(defn any-less-than-or-equal-to
  [incoming-value config-value]
  (some? (some #(less-than-or-equal-to % config-value) incoming-value)))

(defn none-less-than-or-equal-to
  [incoming-value config-value]
  (not (any-less-than-or-equal-to incoming-value config-value)))

(defn every-less-than-or-equal-to
  [incoming-value config-value]
  (every? #(less-than-or-equal-to % config-value) incoming-value))

(defn greater-than
  [incoming-value config-value]
  (> (process-numeric-value incoming-value) config-value))

(defn greater-than-any
  [incoming-value config-value]
  (some? (some #(greater-than incoming-value %) config-value)))

(defn greater-than-none
  [incoming-value config-value]
  (not (greater-than-any incoming-value config-value)))

(defn greater-than-every
  [incoming-value config-value]
  (every? #(greater-than incoming-value %) config-value))

(defn any-greater-than
  [incoming-value config-value]
  (some? (some #(greater-than % config-value) incoming-value)))

(defn none-greater-than
  [incoming-value config-value]
  (not (any-greater-than incoming-value config-value)))

(defn every-greater-than
  [incoming-value config-value]
  (every? #(greater-than % config-value) incoming-value))

(defn greater-than-or-equal-to
  [incoming-value config-value]
  (>= (process-numeric-value incoming-value) config-value))

(defn greater-than-or-equal-to-any
  [incoming-value config-value]
  (some? (some #(greater-than-or-equal-to incoming-value %) config-value)))

(defn greater-than-or-equal-to-none
  [incoming-value config-value]
  (not (greater-than-or-equal-to-any incoming-value config-value)))

(defn greater-than-or-equal-to-every
  [incoming-value config-value]
  (every? #(greater-than-or-equal-to incoming-value %) config-value))

(defn any-greater-than-or-equal-to
  [incoming-value config-value]
  (some? (some #(greater-than-or-equal-to % config-value) incoming-value)))

(defn none-greater-than-or-equal-to
  [incoming-value config-value]
  (not (any-greater-than-or-equal-to incoming-value config-value)))

(defn every-greater-than-or-equal-to
  [incoming-value config-value]
  (every? #(greater-than-or-equal-to % config-value) incoming-value))

(defn collection-size-equals
  [incoming-value config-value]
  (equals (count (set incoming-value)) config-value))

(defn collection-size-not-equals
  [incoming-value config-value]
  (not (collection-size-equals incoming-value config-value)))

(defn collection-size-less-than
  [incoming-value config-value]
  (less-than (count (set incoming-value)) config-value))

(defn collection-size-less-than-or-equal-to
  [incoming-value config-value]
  (less-than-or-equal-to (count (set incoming-value)) config-value))

(defn collection-size-greater-than
  [incoming-value config-value]
  (greater-than (count (set incoming-value)) config-value))

(defn collection-size-greater-than-or-equal-to
  [incoming-value config-value]
  (greater-than-or-equal-to (count (set incoming-value)) config-value))

(defn superset-of
  [incoming-value config-value]
  (cljset/superset? (set incoming-value) (set config-value)))

(defn not-superset-of
  [incoming-value config-value]
  (not (superset-of incoming-value config-value)))

(defn subset-of
  [incoming-value config-value]
  (cljset/subset? (set incoming-value) (set config-value)))

(defn not-subset-of
  [incoming-value config-value]
  (not (subset-of incoming-value config-value)))

(defn any-of
  [incoming-value config-value]
  (contains? (set config-value) incoming-value))

(defn none-of
  [incoming-value config-value]
  (not (any-of incoming-value config-value)))

(defn reversed-any-of
  [incoming-value config-value]
  (contains? (set incoming-value) config-value))

(defn reversed-none-of
  [incoming-value config-value]
  (not (reversed-any-of incoming-value config-value)))

(defn between
  [incoming-value config-value]
  (and (greater-than-or-equal-to incoming-value (first config-value))
       (less-than-or-equal-to incoming-value (second config-value))))

(defn not-between
  [incoming-value config-value]
  (not (between incoming-value config-value)))

(defn reversed-between
  [incoming-value config-value]
  (and (greater-than-or-equal-to config-value (first incoming-value))
       (less-than-or-equal-to config-value (second incoming-value))))

(defn reversed-not-between
  [incoming-value config-value]
  (not (reversed-between incoming-value config-value)))

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

(defn any-regex-match
  [incoming-value config-value]
  (let [config-pattern (re-pattern config-value)]
    (some? (some #(re-find config-pattern %) incoming-value))))

(defn none-regex-match
  [incoming-value config-value]
  (not (any-regex-match incoming-value config-value)))

(defn every-regex-match
  [incoming-value config-value]
  (let [config-pattern (re-pattern config-value)]
    (every? #(re-find config-pattern %) incoming-value)))

(defn not-every-regex-match
  [incoming-value config-value]
  (not (every-regex-match incoming-value config-value)))