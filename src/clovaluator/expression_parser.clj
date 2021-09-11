(ns clovaluator.expression-parser
  (:require [selmer.parser :as parser]
            [clovaluator.logger.log :as log]))

(def ^:private current-namespace *ns*)

(defn generate-expr
  [exp result-map]
  (parser/render exp result-map))

(defn process-result
  [exp result-map]
  (let [substituted-exp (generate-expr exp result-map)]
    (log/info current-namespace "process-result" "logical expression" {:original-exp exp :substituted-exp substituted-exp})
    (eval (read-string substituted-exp))))