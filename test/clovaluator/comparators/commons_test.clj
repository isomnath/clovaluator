(ns clovaluator.comparators.commons-test
  (:require [clojure.test :refer :all]
            [clovaluator.comparators.commons :refer [get-message-field-value]]))

(deftest get-message-field-value-test
  (let [map {:root {:parent-level-1 {:key-1 "pl1-k1"
                                     :key-2 "pl1-k2"
                                     :key-3 "pl1-k3"}
                    :parent-level-2 {:key-1 "pl2-k1"
                                     :key-2 ["t1" "t2" "t3"]}
                    :parent-level-3 {:key-1 "pl3-k1"
                                     :key-2 100.90}}}]
    (testing "should return null when key is not present in map"
      (is (nil? (get-message-field-value map "root.parent-level-1.key-4"))))

    (testing "should return null when key is not present in map"
      (is (= "pl1-k1" (get-message-field-value map "root.parent-level-1.key-1")))
      (is (= "pl1-k2" (get-message-field-value map "root.parent-level-1.key-2")))
      (is (= "pl1-k3" (get-message-field-value map "root.parent-level-1.key-3")))
      (is (= "pl2-k1" (get-message-field-value map "root.parent-level-2.key-1")))
      (is (= ["t1" "t2" "t3"] (get-message-field-value map "root.parent-level-2.key-2")))
      (is (= "pl3-k1" (get-message-field-value map "root.parent-level-3.key-1")))
      (is (= 100.90 (get-message-field-value map "root.parent-level-3.key-2"))))))
