(ns clovaluator.comparators.threshold.executor
  (:require [clovaluator.logger.log :as log]
            [clovaluator.comparators.commons :as commons]
            [clovaluator.comparators.threshold.operator-map :refer [operators-map]]))

(defn- return-false-rule
  [message-field-value expected-value]
  (log/debug *ns* "return-false-rule" "returning false" {:incoming-value message-field-value :expected-value expected-value})
  false)

(defn- operator-function-lookup
  [operator]
  (if-let [operator-fn (get operators-map operator)]
    operator-fn
    (do
      (log/error *ns* "operator-function-lookup" "operator not found" {:operator operator})
      return-false-rule)))

(defn- execute-criterion-internal
  [message operator-name field-name expected-value]
  (let [message-field-value (commons/get-message-field-value message field-name)
        operator-function (operator-function-lookup operator-name)]
    (operator-function message-field-value expected-value)))

(defn- exception-wrapper
  [message operator-name field-name expected-value]
  (try
    (execute-criterion-internal message operator-name field-name expected-value)
    (catch Exception e
      (log/exception *ns* "exception-wrapper" "error while executing rule" e)
      false)))

(defn execute
  [message criterion]
  (let [field-name (:field-name criterion)
        operator-name (:operator criterion)
        expected-value (:value criterion)
        true-return-value (commons/return-values-processor (:expectation-true criterion) true)
        false-return-value (commons/return-values-processor (:expectation-false criterion) false)
        rule (str field-name " | " operator-name " | " expected-value)]
    (log/debug *ns* "exception-wrapper" "executing rule" {:field-name field-name :expected-value expected-value :rule rule})
    (if (exception-wrapper message operator-name field-name expected-value)
      true-return-value
      false-return-value)))