(ns clovaluator.engine.executor-test
  (:require [clojure.test :refer :all]
            [clovaluator.engine.executor :refer [execute-criteria]]))

(deftest execute-criteria-test
  (let [message {:root {:parent-level-1 {:key-1 "pl1-k1"
                                         :key-2 "pl1-k2"
                                         :key-3 "pl1-k1"}
                        :parent-level-2 {:key-1 "pl2-k1"
                                         :key-2 ["t1" "t2" "t3"]}
                        :parent-level-3 {:key-1 "pl3-k1"
                                         :key-2 100.90
                                         :key-3 ["t1" "t2" "t3" "t4"]}}}]
    (testing "should return false when engine type is invalid"
      (let [criteria [{:id         "A"
                       :type       "invalid-comparator"
                       :field-name "root.parent-level-1.key-1"
                       :operator   "equals"
                       :value      "pl1-k1"}]]
        (is (false? (execute-criteria message criteria)))))

    (testing "should return true when criteria evaluation returned true"
      (let [criteria [{:id         "A"
                       :type       "threshold-comparator"
                       :field-name "root.parent-level-1.key-1"
                       :operator   "equals"
                       :value      "pl1-k1"}
                      {:id             "B"
                       :type           "field-comparator"
                       :field-one-name "root.parent-level-1.key-1"
                       :operator       "equals"
                       :field-two-name "root.parent-level-1.key-3"}]]
        (is (true? (execute-criteria message criteria)))))

    (testing "should return true when criteria evaluation returned true with logical expression"
      (let [logical-exp "(and (and (or {{A}} {{B}}) {{C}}) {{D}})"
            criteria [{:id         "A"
                       :type       "threshold-comparator"
                       :field-name "root.parent-level-1.key-1"
                       :operator   "equals"
                       :value      "pl1-k1"}
                      {:id             "B"
                       :type           "field-comparator"
                       :field-one-name "root.parent-level-1.key-1"
                       :operator       "equals"
                       :field-two-name "root.parent-level-1.key-3"}
                      {:id         "C"
                       :type       "threshold-comparator"
                       :field-name "root.parent-level-3.key-2"
                       :operator   "equals"
                       :value      100.90}
                      {:id             "D"
                       :type           "field-comparator"
                       :field-one-name "root.parent-level-2.key-2"
                       :operator       "intersection"
                       :field-two-name "root.parent-level-3.key-3"}]]
        (is (true? (execute-criteria message criteria logical-exp)))))

    (testing "should return false when criteria evaluation returned false with logical expression"
      (let [logical-exp "(and {{A}} {{B}} {{C}})"
            criteria [{:id         "A"
                       :type       "threshold-comparator"
                       :field-name "root.parent-level-1.key-1"
                       :operator   "equals"
                       :value      "pl1-k1"}
                      {:id             "B"
                       :type           "field-comparator"
                       :field-one-name "root.parent-level-1.key-1"
                       :operator       "equals"
                       :field-two-name "root.parent-level-1.key-3"}
                      {:id             "C"
                       :type           "field-comparator"
                       :field-one-name "root.parent-level-1.key-1"
                       :operator       "equals"
                       :field-two-name "root.parent-level-1.key-2"}]]
        (is (false? (execute-criteria message criteria logical-exp)))))

    (testing "should return false when criteria evaluation returned false with logical expression"
      (let [logical-exp "(and (or {{A}} {{B}}) {{C}} {{D}} {{E}})"
            message {:root {:parent-level-1 {:key-1 "pl1-k1"
                                             :key-2 "pl1-k2"
                                             :key-3 "pl1-k1"}
                            :parent-level-2 {:key-1 "pl2-k1"
                                             :key-2 ["t1" "t2" "t3"]}
                            :parent-level-3 {:key-1 "pl3-k1"
                                             :key-2 100.90}
                            :parent-level-4 {:name-1 "random name one"
                                             :name-2 "test field two"}}}
            criteria [{:id         "A"
                       :type       "threshold-comparator"
                       :field-name "root.parent-level-1.key-1"
                       :operator   "equals"
                       :value      "pl1-k1"}
                      {:id             "B"
                       :type           "field-comparator"
                       :field-one-name "root.parent-level-1.key-1"
                       :operator       "equals"
                       :field-two-name "root.parent-level-1.key-3"}
                      {:id             "C"
                       :type           "field-comparator"
                       :field-one-name "root.parent-level-1.key-1"
                       :operator       "equals"
                       :field-two-name "root.parent-level-1.key-2"}]]
        (is (false? (execute-criteria message criteria logical-exp)))))

    (testing "should return true when criteria evaluation returned false with logical expression"
      (let [logical-exp "(and {{A}} {{B}}))"
            message {:root {:parent-level-1    {:key-1 "pl1-k1"
                                                :key-2 "pl1-k2"
                                                :key-3 "pl1-k1"}
                            :parent-level-2    {:key-1 "pl2-k1"
                                                :key-2 ["t1" "t2" "t3"]}
                            :parent-level-3    {:key-1 "pl3-k1"
                                                :key-2 100.90}
                            :parent-level-4    {:name-1 "random name"
                                                :name-2 "test field"}}}
            criteria [{:id         "A"
                       :type       "threshold-comparator"
                       :field-name "root.parent-level-1.key-1"
                       :operator   "equals"
                       :value      "pl1-k1"}
                      {:id             "B"
                       :type           "field-comparator"
                       :field-one-name "root.parent-level-1.key-1"
                       :operator       "equals"
                       :field-two-name "root.parent-level-1.key-3"}]]
        (is (true? (execute-criteria message criteria logical-exp)))))))
