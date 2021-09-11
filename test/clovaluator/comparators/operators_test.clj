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
  (testing "should return true when incoming value is nil(convert to 0 and compare)"
    (is (true? (ops/less-than nil 10))))
  (testing "should return false when incoming value is greater than config value"
    (is (false? (ops/less-than 11 10))))
  (testing "should return false when incoming value is equal to config value"
    (is (false? (ops/less-than 10 10))))
  (testing "should return true when incoming value is less than config value"
    (is (true? (ops/less-than 9 10)))))

(deftest any-less-than-test
  (testing "should return false when incoming value is not less than any of the config values"
    (is (false? (ops/less-than-any 15 [2 3 4]))))

  (testing "should return true when incoming value is less than one of the config values"
    (is (true? (ops/less-than-any 15 [10 12 30]))))

  (testing "should return true when incoming value is less than all of the config values"
    (is (true? (ops/less-than-any 15 [20 25 30])))))

(deftest none-less-than-test
  (testing "should return true when incoming value is not less than any of the config values"
    (is (true? (ops/less-than-none 15 [2 3 4]))))

  (testing "should return false when incoming value is less than one of the config values"
    (is (false? (ops/less-than-none 15 [10 12 30]))))

  (testing "should return false when incoming value is less than all of the config values"
    (is (false? (ops/less-than-none 15 [20 25 30])))))

(deftest every-less-than-test
  (testing "should return false when incoming value is not less than any of the config values"
    (is (false? (ops/less-than-every 15 [2 3 4]))))

  (testing "should return false when incoming value is less than one of the config values"
    (is (false? (ops/less-than-every 15 [10 12 30]))))

  (testing "should return false when incoming value is less than all of the config values"
    (is (true? (ops/less-than-every 15 [20 25 30])))))

(deftest reversed-any-less-than-test
  (testing "should return true when all incoming value is less than config value"
    (is (true? (ops/any-less-than [2 3 4] 15))))

  (testing "should return true when any incoming value is less than config value"
    (is (true? (ops/any-less-than [10 16 30] 15))))

  (testing "should return false when none of the incoming values is less than config value"
    (is (false? (ops/any-less-than [20 25 30] 15)))))

(deftest reversed-none-less-than-test
  (testing "should return false when all incoming value is less than config value"
    (is (false? (ops/none-less-than [2 3 4] 15))))

  (testing "should return false when any incoming value is less than config value"
    (is (false? (ops/none-less-than [10 16 30] 15))))

  (testing "should return true when none of the incoming values is less than config value"
    (is (true? (ops/none-less-than [20 25 30] 15)))))

(deftest reversed-every-less-than-test
  (testing "should return true when all incoming value is less than config value"
    (is (true? (ops/every-less-than [2 3 4] 15))))

  (testing "should return false when any incoming value is less than config value"
    (is (false? (ops/every-less-than [10 16 30] 15))))

  (testing "should return false when none of the incoming values is less than config value"
    (is (false? (ops/every-less-than [20 25 30] 15)))))

(deftest less-than-or-equal-to-test
  (testing "should return true when incoming value is nil(convert to 0 and compare)"
    (is (true? (ops/less-than-or-equal-to nil 10))))
  (testing "should return false when incoming value is greater than config value"
    (is (false? (ops/less-than-or-equal-to 11 10))))
  (testing "should return true when incoming value is equal to config value"
    (is (true? (ops/less-than-or-equal-to 10 10))))
  (testing "should return true when incoming value is less than config value"
    (is (true? (ops/less-than-or-equal-to 9 10)))))

(deftest any-less-than-or-equal-to-test
  (testing "should return false when incoming value is not less than any of the config values"
    (is (false? (ops/less-than-or-equal-to-any 15 [2 3 4]))))

  (testing "should return true when incoming value is less than one of the config values"
    (is (true? (ops/less-than-or-equal-to-any 15 [10 12 30]))))

  (testing "should return true when incoming value is equal to one of the config values"
    (is (true? (ops/less-than-or-equal-to-any 15 [10 15 30]))))

  (testing "should return true when incoming value is less than all of the config values"
    (is (true? (ops/less-than-or-equal-to-any 15 [20 25 30])))))

(deftest none-less-than-or-equal-to-test
  (testing "should return true when incoming value is not less than any of the config values"
    (is (true? (ops/less-than-or-equal-to-none 15 [2 3 4]))))

  (testing "should return false when incoming value is less than one of the config values"
    (is (false? (ops/less-than-or-equal-to-none 15 [10 12 30]))))

  (testing "should return false when incoming value is equal to one of the config values"
    (is (false? (ops/less-than-or-equal-to-none 15 [10 15 30]))))

  (testing "should return false when incoming value is less than all of the config values"
    (is (false? (ops/less-than-or-equal-to-none 15 [20 25 30])))))

(deftest every-less-than-or-equal-to-test
  (testing "should return false when incoming value is not less than any of the config values"
    (is (false? (ops/less-than-or-equal-to-every 15 [2 3 4]))))

  (testing "should return false when incoming value is less than one of the config values"
    (is (false? (ops/less-than-or-equal-to-every 15 [10 12 30]))))

  (testing "should return false when incoming value is less than or equal to all of the config values"
    (is (true? (ops/less-than-or-equal-to-every 15 [20 15 30])))))

(deftest reversed-any-less-than-or-equal-to-test
  (testing "should return false when none of the incoming values is less than or equal to config value"
    (is (false? (ops/any-less-than-or-equal-to [22 23 24] 15))))

  (testing "should return true when any of the incoming values is less than config value"
    (is (true? (ops/any-less-than-or-equal-to [20 12 30] 15))))

  (testing "should return true when any of the incoming values is equal to config value"
    (is (true? (ops/any-less-than-or-equal-to [20 15 30] 15))))

  (testing "should return true when all of the incoming values is less than or equal to config value"
    (is (true? (ops/any-less-than-or-equal-to [10 12 15] 15)))))

(deftest reversed-none-less-than-or-equal-to-test
  (testing "should return true when none of the incoming values is less than or equal to config value"
    (is (true? (ops/none-less-than-or-equal-to [22 23 24] 15))))

  (testing "should return false when any of the incoming values is less than config value"
    (is (false? (ops/none-less-than-or-equal-to [20 12 30] 15))))

  (testing "should return false when any of the incoming values is equal to config value"
    (is (false? (ops/none-less-than-or-equal-to [20 15 30] 15))))

  (testing "should return false when all of the incoming values is less than or equal to config value"
    (is (false? (ops/none-less-than-or-equal-to [10 12 15] 15)))))

(deftest reversed-every-less-than-or-equal-to-test
  (testing "should return true when every incoming value is less than or equal to config value"
    (is (true? (ops/every-less-than-or-equal-to [2 3 15] 15))))

  (testing "should return false when one of the incoming values is greater than config value"
    (is (false? (ops/every-less-than-or-equal-to [10 15 30] 15))))

  (testing "should return false when every incoming value is greater than config value"
    (is (false? (ops/every-less-than-or-equal-to [20 25 30] 15)))))

(deftest greater-than-test
  (testing "should return false when incoming value is nil(convert to 0 and compare)"
    (is (false? (ops/greater-than nil 10))))
  (testing "should return false when incoming value is less than config value"
    (is (false? (ops/greater-than 9 10))))
  (testing "should return false when incoming value is equal to config value"
    (is (false? (ops/greater-than 10 10))))
  (testing "should return true when incoming value is greater than config value"
    (is (true? (ops/greater-than 11 10)))))

(deftest any-greater-than-test
  (testing "should return false when incoming value is not greater than any of the config values"
    (is (false? (ops/greater-than-any 15 [22 23 24]))))

  (testing "should return true when incoming value is greater than one of the config values"
    (is (true? (ops/greater-than-any 15 [10 22 30]))))

  (testing "should return true when incoming value is greater than all of the config values"
    (is (true? (ops/greater-than-any 45 [20 25 30])))))

(deftest none-greater-than-test
  (testing "should return true when incoming value is not greater than any of the config values"
    (is (true? (ops/greater-than-none 15 [22 23 24]))))

  (testing "should return false when incoming value is greater than one of the config values"
    (is (false? (ops/greater-than-none 15 [10 22 30]))))

  (testing "should return false when incoming value is greater than all of the config values"
    (is (false? (ops/greater-than-none 45 [20 25 30])))))

(deftest every-greater-than-test
  (testing "should return false when incoming value is not greater than any of the config values"
    (is (false? (ops/greater-than-every 15 [22 23 24]))))

  (testing "should return false when incoming value is greater than one of the config values"
    (is (false? (ops/greater-than-every 15 [10 22 30]))))

  (testing "should return true when incoming value is greater than all of the config values"
    (is (true? (ops/greater-than-every 45 [20 25 30])))))

(deftest reversed-any-greater-than-test
  (testing "should return false when none of the incoming values is greater than config value"
    (is (false? (ops/any-greater-than [12 13 14] 15))))

  (testing "should return false when one of the incoming values is equal to config value"
    (is (false? (ops/any-greater-than [10 15 9] 15))))

  (testing "should return true when any of the incoming values is greater than config value"
    (is (true? (ops/any-greater-than [20 5 3] 15))))

  (testing "should return true when all of the incoming values is greater than config value"
    (is (true? (ops/any-greater-than [20 25 33] 15)))))

(deftest reversed-none-greater-than-test
  (testing "should return true when none of the incoming values is greater than config value"
    (is (true? (ops/none-greater-than [12 13 14] 15))))

  (testing "should return true when one of the incoming values is equal to config value"
    (is (true? (ops/none-greater-than [10 15 9] 15))))

  (testing "should return false when any of the incoming values is greater than config value"
    (is (false? (ops/none-greater-than [20 5 3] 15))))

  (testing "should return false when all of the incoming values is greater than config value"
    (is (false? (ops/none-greater-than [20 25 33] 15)))))

(deftest reversed-every-greater-than-test
  (testing "should return false when none of the incoming values is greater than config value"
    (is (false? (ops/every-greater-than [12 13 14] 15))))

  (testing "should return false when one of the incoming values is equal to config value"
    (is (false? (ops/every-greater-than [10 15 9] 15))))

  (testing "should return false when any of the incoming values is greater than config value"
    (is (false? (ops/every-greater-than [20 5 3] 15))))

  (testing "should return true when all of the incoming values is greater than config value"
    (is (true? (ops/every-greater-than [20 25 33] 15)))))

(deftest greater-than-or-equal-to-test
  (testing "should return false when incoming value is nil(convert to 0 and compare)"
    (is (false? (ops/greater-than-or-equal-to nil 10))))
  (testing "should return false when incoming value is less than config value"
    (is (false? (ops/greater-than-or-equal-to 9 10))))
  (testing "should return true when incoming value is equal to config value"
    (is (true? (ops/greater-than-or-equal-to 10 10))))
  (testing "should return true when incoming value is greater than config value"
    (is (true? (ops/greater-than-or-equal-to 11 10)))))

(deftest any-greater-than-or-equal-to-test
  (testing "should return false when incoming value is not greater than any of the config values"
    (is (false? (ops/greater-than-or-equal-to-any 15 [22 23 24]))))

  (testing "should return true when incoming value is greater than one of the config values"
    (is (true? (ops/greater-than-or-equal-to-any 15 [10 22 30]))))

  (testing "should return true when incoming value is equal to one of the config values"
    (is (true? (ops/greater-than-or-equal-to-any 15 [10 15 30]))))

  (testing "should return true when incoming value is greater than all of the config values"
    (is (true? (ops/greater-than-or-equal-to-any 45 [20 25 30])))))

(deftest none-greater-than-or-equal-to
  (testing "should return true when incoming value is not greater than any of the config values"
    (is (true? (ops/greater-than-or-equal-to-none 15 [22 23 24]))))

  (testing "should return false when incoming value is greater than one of the config values"
    (is (false? (ops/greater-than-or-equal-to-none 15 [10 22 30]))))

  (testing "should return false when incoming value is equal to one of the config values"
    (is (false? (ops/greater-than-or-equal-to-none 15 [10 15 30]))))

  (testing "should return false when incoming value is greater than all of the config values"
    (is (false? (ops/greater-than-or-equal-to-none 45 [20 25 30])))))

(deftest every-greater-than-or-equal-to-test
  (testing "should return false when incoming value is not greater than any of the config values"
    (is (false? (ops/greater-than-or-equal-to-every 15 [22 23 24]))))

  (testing "should return false when incoming value is greater than one of the config values"
    (is (false? (ops/greater-than-or-equal-to-every 15 [10 22 30]))))

  (testing "should return false when incoming value is equal to one of the config values"
    (is (false? (ops/greater-than-or-equal-to-every 15 [10 15 30]))))

  (testing "should return true when incoming value is greater than all of the config values"
    (is (true? (ops/greater-than-or-equal-to-every 45 [20 25 30])))))

(deftest reversed-any-greater-than-or-equal-to-test
  (testing "should return false when none of the incoming values is greater than or equal to config value"
    (is (false? (ops/any-greater-than-or-equal-to [12 13 14] 15))))

  (testing "should return true when one of the incoming values is equal to config value"
    (is (true? (ops/any-greater-than-or-equal-to [10 15 9] 15))))

  (testing "should return true when any of the incoming values is greater than config value"
    (is (true? (ops/any-greater-than-or-equal-to [20 5 3] 15))))

  (testing "should return true when all of the incoming values is greater than config value"
    (is (true? (ops/any-greater-than-or-equal-to [20 15 33] 15)))))

(deftest reversed-none-greater-than-or-equal-to-test
  (testing "should return true when none of the incoming values is greater than or equal to config value"
    (is (true? (ops/none-greater-than-or-equal-to [12 13 14] 15))))

  (testing "should return true when one of the incoming values is equal to config value"
    (is (false? (ops/none-greater-than-or-equal-to [10 15 9] 15))))

  (testing "should return true when any of the incoming values is greater than config value"
    (is (false? (ops/none-greater-than-or-equal-to [20 5 3] 15))))

  (testing "should return true when all of the incoming values is greater than config value"
    (is (false? (ops/none-greater-than-or-equal-to [20 15 33] 15)))))

(deftest reversed-every-greater-than-or-equal-to-test
  (testing "should return false when none of the incoming values is greater than or equal to config value"
    (is (false? (ops/every-greater-than-or-equal-to [12 13 14] 15))))

  (testing "should return false when one of the incoming values is equal to config value"
    (is (false? (ops/every-greater-than-or-equal-to [10 15 9] 15))))

  (testing "should return true when any of the incoming values is greater than config value"
    (is (false? (ops/every-greater-than-or-equal-to [20 5 3] 15))))

  (testing "should return true when all of the incoming values is greater than or equal to config value"
    (is (true? (ops/every-greater-than-or-equal-to [20 15 33] 15)))))

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

(deftest subset-test
  (testing "should return false when incoming value is not a subset of config value"
    (is (false? (ops/subset-of [1 2 3 4] [1 2 3]))))
  (testing "should return true when incoming value is an equivalent subset of config value"
    (is (true? (ops/subset-of [1 2 3] [1 2 3]))))
  (testing "should return true when incoming value is a subset of config value"
    (is (true? (ops/subset-of [1 2 3] [1 2 3 4])))))

(deftest not-subset-test
  (testing "should return true when incoming value is not a subset of config value"
    (is (true? (ops/not-subset-of [1 2 3 4] [1 2 3]))))
  (testing "should return false when incoming value is an equivalent subset of config value"
    (is (false? (ops/not-subset-of [1 2 3] [1 2 3]))))
  (testing "should return false when incoming value is a subset of config value"
    (is (false? (ops/not-subset-of [1 2 3] [1 2 3 4])))))

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
  (testing "should return true when incoming value matches config value regex with first letter in pattern"
    (is (true? (ops/regex-match "ADBCR8921T" "[A-F]")))))

(deftest regex-not-match-test
  (testing "should return true when incoming value matches config value regex"
    (is (false? (ops/regex-not-match "hello, world" "hello.*"))))
  (testing "should return false when incoming value does not match config value regex"
    (is (true? (ops/regex-not-match "HELLO, WORLD" "hello"))))
  (testing "should return true when incoming value matches config value regex with digits pattern"
    (is (false? (ops/regex-not-match "1231241432" "\\d+"))))
  (testing "should return true when incoming value matches config value regex with first letter in pattern"
    (is (false? (ops/regex-not-match "ADBCR8921T" "[A-F]")))))

(deftest any-regex-match-test
  (testing "should return true when incoming values has at least one match with config value regex"
    (is (true? (ops/any-regex-match ["hello, world" "test"] "hello.*"))))
  (testing "should return false when incoming values does not have one match with config value regex"
    (is (false? (ops/any-regex-match ["test-1", "test-2"] "hello.*")))))

(deftest none-regex-match-test
  (testing "should return false when incoming values has at least one match with config value regex"
    (is (false? (ops/none-regex-match ["hello, world" "test"] "hello.*"))))
  (testing "should return true when incoming values does not have one match with config value regex"
    (is (true? (ops/none-regex-match ["test-1", "test-2"] "hello.*")))))

(deftest every-regex-match-test
  (testing "should return true when every incoming values match with config value regex"
    (is (true? (ops/every-regex-match ["hello, world" "hello, test"] "hello.*"))))
  (testing "should return false when even one of the incoming values do not match with config value regex"
    (is (false? (ops/every-regex-match ["hello, world" "test"] "hello.*")))))

(deftest not-every-regex-match-test
  (testing "should return false when every incoming values match with config value regex"
    (is (false? (ops/not-every-regex-match ["hello, world" "hello, test"] "hello.*"))))
  (testing "should return true when even one of the incoming values do not match with config value regex"
    (is (true? (ops/not-every-regex-match ["hello, world" "test"] "hello.*")))))

(deftest reversed-any-of-test
  (testing "should return false when config value is not any of incoming value"
    (is (false? (ops/reversed-any-of [2 3 4] 1))))
  (testing "should return true when config value is any of incoming value"
    (is (true? (ops/reversed-any-of [1 2 3 4] 1)))))

(deftest reversed-none-of-test
  (testing "should return true when config value is not any of incoming value"
    (is (true? (ops/reversed-none-of [2 3 4] 1))))
  (testing "should return false when config value is any of incoming value"
    (is (false? (ops/reversed-none-of [1 2 3 4] 1)))))

(deftest reversed-between-test
  (testing "should return true when config value falls between incoming values"
    (is (true? (ops/reversed-between [3 5] 4))))
  (testing "should return false when config value is greater than max boundary of incoming value"
    (is (false? (ops/reversed-between [3 6] 7))))
  (testing "should return false when config value is lesser than min boundary of incoming value"
    (is (false? (ops/reversed-between [3 6] 2)))))

(deftest reversed-not-between-test
  (testing "should return false when config value falls between incoming values"
    (is (false? (ops/reversed-not-between [3 5] 4))))
  (testing "should return true when config value is greater than max boundary incoming value"
    (is (true? (ops/reversed-not-between [3 6] 7))))
  (testing "should return true when config value is lesser than min boundary of incoming value"
    (is (true? (ops/reversed-not-between [3 6] 2)))))

(deftest collection-size-equals-test
  (testing "should return true if size of incoming collection value is equal to config value"
    (is (true? (ops/collection-size-equals [1 2 34] 3))))

  (testing "should return false if size of incoming collection value is nil"
    (is (false? (ops/collection-size-equals nil 2))))

  (testing "should return false if size of incoming collection value is empty"
    (is (false? (ops/collection-size-equals [] 2))))

  (testing "should return false if size of incoming collection value is not equal to config value"
    (is (false? (ops/collection-size-equals [1 2 34] 2)))))

(deftest collection-size-not-equals-test
  (testing "should return false if size of incoming collection value is equal to config value"
    (is (false? (ops/collection-size-not-equals [1 2 34] 3))))

  (testing "should return true if size of incoming collection value is nil"
    (is (true? (ops/collection-size-not-equals nil 2))))

  (testing "should return true if size of incoming collection value is empty"
    (is (true? (ops/collection-size-not-equals [] 2))))

  (testing "should return true if size of incoming collection value is not equal to config value"
    (is (true? (ops/collection-size-not-equals [1 2 34] 2)))))

(deftest collection-size-less-than-test
  (testing "should return true if size of incoming collection value is less than config value"
    (is (true? (ops/collection-size-less-than [1 2] 3))))

  (testing "should return true if size of incoming collection value is nil"
    (is (true? (ops/collection-size-less-than nil 2))))

  (testing "should return true if size of incoming collection value is empty"
    (is (true? (ops/collection-size-less-than [] 2))))

  (testing "should return false if size of incoming collection value is not less than config value"
    (is (false? (ops/collection-size-less-than [1 2 34] 2)))))

(deftest collection-size-less-than-or-equal-to-test
  (testing "should return true if size of incoming collection value is less than config value"
    (is (true? (ops/collection-size-less-than-or-equal-to [1 2] 3))))

  (testing "should return true if size of incoming collection value is equal to config value"
    (is (true? (ops/collection-size-less-than-or-equal-to [1 2 34] 3))))

  (testing "should return true if size of incoming collection value is nil"
    (is (true? (ops/collection-size-less-than-or-equal-to nil 2))))

  (testing "should return true if size of incoming collection value is empty"
    (is (true? (ops/collection-size-less-than-or-equal-to [] 2))))

  (testing "should return false if size of incoming collection value is not less than or equal to config value"
    (is (false? (ops/collection-size-less-than-or-equal-to [1 2 34] 2)))))

(deftest collection-size-greater-than-test
  (testing "should return true if size of incoming collection value is greater than config value"
    (is (true? (ops/collection-size-greater-than [1 2 34 5] 3))))

  (testing "should return false if size of incoming collection value is nil"
    (is (false? (ops/collection-size-greater-than nil 2))))

  (testing "should return false if size of incoming collection value is empty"
    (is (false? (ops/collection-size-greater-than [] 2))))

  (testing "should return false if size of incoming collection value is not greater than config value"
    (is (false? (ops/collection-size-greater-than [1 2] 2)))))

(deftest collection-size-greater-than-or-equal-to-test
  (testing "should return true if size of incoming collection value is greater than config value"
    (is (true? (ops/collection-size-greater-than-or-equal-to [1 2 34 5] 3))))

  (testing "should return true if size of incoming collection value is equal to config value"
    (is (true? (ops/collection-size-greater-than-or-equal-to [1 2 34] 3))))

  (testing "should return false if size of incoming collection value is nil"
    (is (false? (ops/collection-size-greater-than-or-equal-to nil 2))))

  (testing "should return false if size of incoming collection value is empty"
    (is (false? (ops/collection-size-greater-than-or-equal-to [] 2))))

  (testing "should return false if size of incoming collection value is not greater than or equal to config value"
    (is (false? (ops/collection-size-greater-than-or-equal-to [1 2] 3)))))
