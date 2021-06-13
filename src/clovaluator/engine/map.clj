(ns clovaluator.engine.map
  (:require [clovaluator.comparators.field.executor :as fce]
            [clovaluator.comparators.threshold.executor :as te]))

(def ^:private executor-type-map
  {"field-comparator"   fce/execute
   "threshold-comparator" te/execute})

(defn get-executor
  [comparator-type]
  (get executor-type-map comparator-type))