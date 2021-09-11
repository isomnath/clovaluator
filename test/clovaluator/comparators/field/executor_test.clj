(ns clovaluator.comparators.field.executor-test
  (:require [clojure.test :refer :all]
            [clovaluator.comparators.field.executor :refer [execute]]))

(deftest execute-test
  (let [message {:root {:parent-level-1 {:key-1 "pl1-k1"
                                         :key-2 "pl1-k2"
                                         :key-3 "pl1-k2"}
                        :parent-level-2 {:key-1 "pl2-k1"
                                         :key-2 ["t1" "t2" "t3"]}
                        :parent-level-3 {:key-1 "pl3-k1"
                                         :key-2 100.90}}}]

    (testing "should return false when operator does not exist"
      (let [criterion {:id             "A"
                       :field-one-name "root.parent-level-2.key-2"
                       :operator       "invalid-comparator"
                       :field-two-name "root.parent-level-2.key-1"}]
        (is (false? (execute message criterion)))))

    (testing "should return false when null pointer exception occurs in evaluating criteria as field is not present"
      (let [criterion {:field-one-name    nil
                       :operator          "equals"
                       :field-two-name    "root.parent-level-6.key-2"
                       :expectation-true  true
                       :expectation-false false}]
        (is (false? (execute message criterion)))))

    (testing "should return false when exception occurs in evaluating criteria"
      (let [criterion {:field-one-name    "root.parent-level-3.key-1"
                       :operator          "superset-of"
                       :field-two-name    "root.parent-level-3.key-2"
                       :expectation-true  true
                       :expectation-false false}]
        (is (false? (execute message criterion)))))

    (testing "equals test - should return false when all criteria do not match"
      (let [criterion {:id             "A"
                       :field-one-name "root.parent-level-1.key-2"
                       :operator       "equals"
                       :field-two-name "root.parent-level-1.key-1"}]
        (is (false? (execute message criterion)))))

    (testing "equals test - should return true when all criteria match"
      (let [criterion {:id             "A"
                       :field-one-name "root.parent-level-1.key-2"
                       :operator       "equals"
                       :field-two-name "root.parent-level-1.key-3"}]
        (is (true? (execute message criterion)))))

    (testing "equals test - should return expectation-true when criterion match"
      (let [criterion {:id                "A"
                       :field-one-name    "root.parent-level-1.key-2"
                       :operator          "equals"
                       :field-two-name    "root.parent-level-1.key-3"
                       :expectation-true  "ABC"
                       :expectation-false "DEF"}]
        (is (= "ABC" (execute message criterion)))))

    (testing "equals test - should return expectation-false when criterion does not match"
      (let [criterion {:id                "A"
                       :field-one-name    "root.parent-level-1.key-2"
                       :operator          "equals"
                       :field-two-name    "root.parent-level-1.key-1"
                       :expectation-true  "ABC"
                       :expectation-false "DEF"}]
        (is (= "DEF" (execute message criterion)))))))
