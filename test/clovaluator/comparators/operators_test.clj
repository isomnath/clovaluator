(ns clovaluator.comparators.operators-test
  (:require [clojure.test :refer :all]
            [clovaluator.comparators.operators :as ops]))

(deftest equals-test
  (testing "should return false when string values are not equal"
    (is (false? (ops/equals "abc" "def"))))
  (testing "should return true when string values are equal"
    (is (true? (ops/equals "abc" "abc"))))

  (testing "should return false when integer values are not equal"
    (is (false? (ops/equals 12 10))))
  (testing "should return true when integer values are equal"
    (is (true? (ops/equals 10 10))))

  (testing "should return false when float values are not equal"
    (is (false? (ops/equals 10.10 12.10))))
  (testing "should return true when float values are equal"
    (is (true? (ops/equals 10.99 10.99))))

  (testing "should return false when boolean values are not equal"
    (is (false? (ops/equals true false))))
  (testing "should return true when boolean values are equal and both are true"
    (is (true? (ops/equals true true))))
  (testing "should return true when boolean values are equal and both are false"
    (is (true? (ops/equals false false)))))

(deftest not-equals-test
  (testing "should return true when string values are not equal"
    (is (true? (ops/not-equals "abc" "def"))))
  (testing "should return false when string values are equal"
    (is (false? (ops/not-equals "abc" "abc"))))

  (testing "should return true when integer values are not equal"
    (is (true? (ops/not-equals 12 10))))
  (testing "should return false when integer values are equal"
    (is (false? (ops/not-equals 10 10))))

  (testing "should return true when float values are not equal"
    (is (true? (ops/not-equals 10.10 12.10))))
  (testing "should return false when float values are equal"
    (is (false? (ops/not-equals 10.99 10.99))))

  (testing "should return true when boolean values are not equal"
    (is (true? (ops/not-equals true false))))
  (testing "should return false when boolean values are equal and both are true"
    (is (false? (ops/not-equals true true))))
  (testing "should return false when boolean values are equal and both are false"
    (is (false? (ops/not-equals false false)))))

(deftest less-than-test
  (testing "should return false when incoming value is greater than config value"
    (is (false? (ops/less-than 11 10))))
  (testing "should return false when incoming value is equal to config value"
    (is (false? (ops/less-than 10 10))))
  (testing "should return true when incoming value is less than config value"
    (is (true? (ops/less-than 9 10)))))

(deftest less-than-or-equal-to-test
  (testing "should return false when incoming value is greater than config value"
    (is (false? (ops/less-than-or-equal-to 11 10))))
  (testing "should return true when incoming value is equal to config value"
    (is (true? (ops/less-than-or-equal-to 10 10))))
  (testing "should return true when incoming value is less than config value"
    (is (true? (ops/less-than-or-equal-to 9 10)))))

(deftest greater-than-test
  (testing "should return false when incoming value is less than config value"
    (is (false? (ops/greater-than 9 10))))
  (testing "should return false when incoming value is equal to config value"
    (is (false? (ops/greater-than 10 10))))
  (testing "should return true when incoming value is greater than config value"
    (is (true? (ops/greater-than 11 10)))))

(deftest greater-than-or-equal-to-test
  (testing "should return false when incoming value is less than config value"
    (is (false? (ops/greater-than-or-equal-to 9 10))))
  (testing "should return true when incoming value is equal to config value"
    (is (true? (ops/greater-than-or-equal-to 10 10))))
  (testing "should return true when incoming value is greater than config value"
    (is (true? (ops/greater-than-or-equal-to 11 10)))))

(deftest superset-test
  (testing "should return false when incoming value is not a superset of config value"
    (is (false? (ops/superset-of [1 2 3] [1 2 3 4]))))
  (testing "should return true when incoming value is an equivalent superset of config
  value"
    (is (true? (ops/superset-of [1 2 3] [1 2 3]))))
  (testing "should return true when incoming value is a superset of config value"
    (is (true? (ops/superset-of [1 2 3] [1 2])))))

(deftest not-superset-test
  (testing "should return true when incoming value is not a superset of config value"
    (is (true? (ops/not-superset-of [1 2] [1 2 3 4]))))
  (testing "should return false when incoming value is an equivalent superset of config value"
    (is (false? (ops/not-superset-of [1 2 3 4] [1 2 3 4]))))
  (testing "should return false when incoming value is a superset of config value"
    (is (false? (ops/not-superset-of [1 2 3 4] [1 2 3])))))

(deftest any-of-test
  (testing "should return false when incoming value is not any of config value"
    (is (false? (ops/any-of 1 [2 3 4]))))
  (testing "should return true when incoming value is any of config value"
    (is (true? (ops/any-of 1 [1 2 3 4])))))

(deftest none-of-test
  (testing "should return true when incoming value is not any of config value"
    (is (true? (ops/none-of 1 [2 3 4]))))
  (testing "should return false when incoming value is any of config value"
    (is (false? (ops/none-of 1 [1 2 3 4])))))

(deftest between-test
  (testing "should return true when incoming value falls between config values"
    (is (true? (ops/between 4 [3 5]))))
  (testing "should return false when incoming value is greater than max boundary of config value"
    (is (false? (ops/between 7 [3 6]))))
  (testing "should return false when incoming value is lesser than min boundary of config value"
    (is (false? (ops/between 2 [3 6])))))

(deftest not-between-test
  (testing "should return false when incoming value falls between config values"
    (is (false? (ops/not-between 4 [3 5]))))
  (testing "should return true when incoming value is greater than max boundary config value"
    (is (true? (ops/not-between 7 [3 6]))))
  (testing "should return true when incoming value is lesser than min boundary of config value"
    (is (true? (ops/not-between 2 [3 6])))))

(deftest intersection-test
  (testing "should return true when incoming values and config values has an intersection"
    (is (true? (ops/intersection [1 2 4] [2 4 6]))))
  (testing "should return false when incoming values and config values do not have an
  intersection"
    (is (false? (ops/intersection [1 5 7] [2 4 6])))))

(deftest not-intersection-test
  (testing "should return false when incoming values and config values has an intersection"
    (is (false? (ops/not-intersection [1 2 4] [2 4 6]))))
  (testing "should return true when incoming values and config values do not have an intersection"
    (is (true? (ops/not-intersection [1 5 7] [2 4 6])))))

(deftest regex-match-test
  (testing "should return true when incoming value matches config value regex"
    (is (true? (ops/regex-match "hello, world" "hello.*"))))
  (testing "should return false when incoming value does not match config value regex"
    (is (false? (ops/regex-match "HELLO, WORLD" "hello"))))
  (testing "should return true when incoming value matches config value regex with digits pattern"
    (is (true? (ops/regex-match "1231241432" "\\d+"))))
  (testing "should return true when incoming value matches config value regex with first letter
  in pattern"
    (is (true? (ops/regex-match "ADBCR8921T" "[A-F]")))))

(deftest regex-not-match-test
  (testing "should return true when incoming value matches config value regex"
    (is (false? (ops/regex-not-match "hello, world" "hello.*"))))
  (testing "should return false when incoming value does not match config value regex"
    (is (true? (ops/regex-not-match "HELLO, WORLD" "hello"))))
  (testing "should return true when incoming value matches config value regex with digits pattern"
    (is (false? (ops/regex-not-match "1231241432" "\\d+"))))
  (testing "should return true when incoming value matches config value regex with first letter
  in pattern"
    (is (false? (ops/regex-not-match "ADBCR8921T" "[A-F]")))))
