(ns clovaluator.comparators.threshold.executor-test
  (:require [clojure.test :refer :all]
            [clovaluator.comparators.threshold.executor :refer [execute]]))

(deftest execute-test
  (let [message {:root {:parent-level-1 {:key-1 "pl1-k1"
                                         :key-2 "pl1-k2"}
                        :parent-level-2 {:key-1 "pl2-k1"
                                         :key-2 ["t1" "t2" "t3"]}
                        :parent-level-3 {:key-1 "pl3-k1"
                                         :key-2 100.90}}}]
    (testing "should return false when operator does not exist"
      (let [criterion {:id         "A"
                       :field-name "root.parent-level-2.key-2"
                       :operator   "invalid-comparator"
                       :value      ["t1" "t2" "t3" "t4"]}]
        (is (false? (execute message criterion)))))

    (testing "should return false when exception occurs in evaluating criteria"
      (let [criterion {:id         "A"
                       :field-name "root.parent-level-2.key-1"
                       :operator   "between"
                       :value      [101.0 110.90]}]
        (is (false? (execute message criterion)))))

    (testing "should return false when null pointer exception occurs in evaluating criteria as field name is missing"
      (let [criterion {:id         "A"
                       :operator   "between"
                       :value      [101.0 110.90]}]
        (is (false? (execute message criterion)))))

    (testing "equals test - should return false when all criteria do not match"
      (let [criterion {:id         "A"
                       :field-name "root.parent-level-1.key-1"
                       :operator   "equals"
                       :value      "pl1-k3"}]
        (is (false? (execute message criterion)))))

    (testing "criterion test match test - should return true when criterion matches"
      (let [criterion {:id         "A"
                       :field-name "root.parent-level-1.key-1"
                       :operator   "equals"
                       :value      "pl1-k1"}]
        (is (true? (execute message criterion)))))

    (testing "criterion test match test - should return expectation-true when criterion matches"
      (let [criterion {:id                "A"
                       :field-name        "root.parent-level-1.key-1"
                       :operator          "equals"
                       :value             "pl1-k1"
                       :expectation-true  "ABC"
                       :expectation-false "DEF"}]
        (is (= "ABC" (execute message criterion)))))

    (testing "criterion test match test - should return expectation-false when criterion does not match"
      (let [criterion {:id                "A"
                       :field-name        "root.parent-level-1.key-1"
                       :operator          "equals"
                       :value             "pl1-k2"
                       :expectation-true  "ABC"
                       :expectation-false "DEF"}]
        (is (= "DEF" (execute message criterion)))))))
