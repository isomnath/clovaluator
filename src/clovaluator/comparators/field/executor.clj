(ns clovaluator.comparators.field.executor
  (:require [clovaluator.logger.log :as log]
            [clovaluator.comparators.commons :as commons]
            [clovaluator.comparators.field.operator-map :refer [operators-map]]))

(def ^:private current-namespace *ns*)

(defn- return-false-rule
  [message-field-one-value message-field-two-value]
  (log/error current-namespace "return-false-rule" "returning false" {:message-field-one-value message-field-one-value
                                                                      :message-field-two-value message-field-two-value})
  false)

(defn- operator-function-lookup
  [operator]
  (if-let [operator-fn (get operators-map operator)]
    operator-fn
    (do
      (log/error current-namespace "operator-function-lookup" "operator not found" {:operator operator})
      return-false-rule)))

(defn- execute-criterion-internal
  [message operator-name field-one-name field-two-name]
  (let [message-field-one-value (commons/get-message-field-value message field-one-name)
        message-field-two-value (commons/get-message-field-value message field-two-name)
        operator-function (operator-function-lookup operator-name)]
    (operator-function message-field-one-value message-field-two-value)))

(defn- exception-wrapper
  [message operator-name field-one-name field-two-name]
  (try
    (execute-criterion-internal message operator-name field-one-name field-two-name)
    (catch NullPointerException e
      (log/exception current-namespace "exception-wrapper" "null pointer exception while executing rule"
                     {:field-one-name field-one-name :field-two-name field-two-name :operator operator-name} e)
      false)
    (catch Exception e
      (log/exception current-namespace "exception-wrapper" "error while executing rule"
                     {:field-one-name field-one-name :field-two-name field-two-name :operator operator-name} e)
      false)))

(defn execute
  [message criterion]
  (let [field-one-name (:field-one-name criterion)
        operator-name (:operator criterion)
        field-two-name (:field-two-name criterion)
        true-return-value (commons/return-values-processor (:expectation-true criterion) true)
        false-return-value (commons/return-values-processor (:expectation-false criterion) false)
        rule (str field-one-name " | " operator-name " | " field-two-name)]
    (log/info *ns* "execute" "executing rule"
              {:message        message :criteria criterion
               :field-one-name field-one-name :field-two-name field-two-name :rule rule})
    (if (exception-wrapper message operator-name field-one-name field-two-name)
      true-return-value
      false-return-value)))