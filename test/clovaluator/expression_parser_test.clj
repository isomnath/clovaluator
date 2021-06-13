(ns clovaluator.expression-parser-test
  (:require [clojure.test :refer :all]
            [clovaluator.expression-parser :refer :all]))

(deftest process-result-test
  (testing "should return true when expression evaluates true - case 1"
    (let [exp "(and (or {{A}} {{B}}) {{C}})"
          result-map {:A true :B false :C true}]
      (is (true? (process-result exp result-map)))))

  (testing "should return false when expression evaluates false"
    (let [exp "(and (or {{A}} {{B}}) {{C}})"
          result-map {:A false :B false :C true}]
      (is (false? (process-result exp result-map))))))
