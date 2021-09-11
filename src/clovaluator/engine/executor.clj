(ns clovaluator.engine.executor
  (:require [clojure.string :refer [join]]
            [clovaluator.logger.log :as log]
            [clovaluator.engine.map :refer [get-executor]]
            [clovaluator.expression-parser :refer [process-result]]))

(def ^:private current-namespace *ns*)

(defn- return-false-type
  [message criterion]
  (log/error current-namespace "return-false-type" "returning false" {:message message :criterion criterion})
  false)

(defn- get-comparator
  [type]
  (if-let [comparator (get-executor type)]
    comparator
    (do
      (log/error current-namespace "get-comparator" "comparator type not found" {:comparator-type type})
      return-false-type)))

(defn- generate-default-logic-gate-exp
  [criteria]
  (let [exp-template ["(and " ")"]
        criteria-string (join " " (map #(str "{{" (:id %) "}}") criteria))]
    (str (first exp-template) criteria-string (last exp-template))))

(defn execute-criterion
  [message criterion]
  (let [type (:type criterion)
        comparator (get-comparator type)]
    (comparator message criterion)))

(defn- execute-single-criterion
  [message criterion]
  (let [id (keyword (:id criterion))]
    {id (execute-criterion message criterion)}))

(defn execute-criteria
  ([message criteria]
   (let [default-logic-exp (generate-default-logic-gate-exp criteria)]
     (log/info current-namespace "execute-criteria" "default logic expression" {:exp default-logic-exp})
     (execute-criteria message criteria default-logic-exp)))
  ([message criteria logic-gate-exp]
   (log/info current-namespace "execute-criteria" "executing rule" {:message message :criteria criteria})
   (process-result logic-gate-exp (into {} (map #(execute-single-criterion message %) criteria)))))