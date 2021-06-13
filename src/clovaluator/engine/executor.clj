(ns clovaluator.engine.executor
  (:require [clojure.string :refer [join]]
            [clovaluator.logger.log :as log]
            [clovaluator.engine.map :refer [get-executor]]
            [clovaluator.expression-parser :refer [process-result]]))

(defn- return-false-type
  [message criterion]
  (log/debug *ns* "return-false-type" "returning false" {:message message :criterion criterion})
  false)

(defn- get-comparator
  [type]
  (if-let [comparator (get-executor type)]
    comparator
    (do
      (log/error *ns* "get-comparator" "comparator type not found" {:comparator-type type})
      return-false-type)))

(defn execute-criterion
  [message criterion]
  (let [type (:type criterion)
        comparator (get-comparator type)]
    (comparator message criterion)))

(defn- generate-default-logic-gate-exp
  [criteria]
  (let [prefix "(and "
        suffix ")"
        criteria-string (join " " (map #(str "{{" (:id %) "}}") criteria))]
    (str prefix criteria-string suffix)))

(defn- execute-single-criterion
  [message criterion]
  (let [id (keyword (:id criterion))]
    {id (execute-criterion message criterion)}))

(defn execute-criteria
  ([message criteria]
   (let [default-logic-exp (generate-default-logic-gate-exp criteria)]
     (log/info *ns* "execute-criteria" "default logic expression" {:exp default-logic-exp})
     (execute-criteria message criteria default-logic-exp)))
  ([message criteria logic-gate-exp]
   (log/info *ns* "execute-criteria" "executing rule" {:message message :criteria criteria})
   (process-result logic-gate-exp (into {} (map #(execute-single-criterion message %) criteria)))))