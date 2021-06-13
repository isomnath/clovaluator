(ns clovaluator.comparators.commons
  (:require [clojure.string :as str]))

(defn get-message-field-value
  [message field-name]
  (let [field-list (str/split field-name #"\.")
        nested-key-sequence (map keyword field-list)]
    (get-in message nested-key-sequence)))

(defn return-values-processor
  [return-value default-return-value]
  (if (nil? return-value)
    default-return-value
    return-value))