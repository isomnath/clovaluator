# Clovaluator

[![Build Status](https://app.travis-ci.com/isomnath/clovaluator.svg?branch=master)](https://app.travis-ci.com/github/isomnath/clovaluator)
[![Coverage Status](https://coveralls.io/repos/github/isomnath/clovaluator/badge.svg?branch=master)](https://coveralls.io/github/taniarascia/chip8?branch=master)

I am a Clojure Library dimension agnostic criteria comparator.
I expose the following threshold comparison operators:
```
* equals
* not-equals
* less-than
* less-than-any
* less-than-none
* less-than-every
* any-less-than
* none-less-than
* every-less-than
* greater-than
* greater-than-any
* greater-than-none
* greater-than-every
* any-greater-than
* none-greater-than
* every-greater-than
* less-than-or-equal-to
* less-than-or-equal-to-any
* less-than-or-equal-to-none
* less-than-or-equal-to-every
* any-less-than-or-equal-to
* none-less-than-or-equal-to
* every-less-than-or-equal-to
* greater-than-or-equal-to
* greater-than-or-equal-to-any
* greater-than-or-equal-to-none
* greater-than-or-equal-to-every
* any-greater-than-or-equal-to
* none-greater-than-or-equal-to
* every-greater-than-or-equal-to
* superset-of
* not-superset-of
* subset-of
* not-subset-of
* any-of
* none-of
* reversed-any-of
* reversed-none-of
* between
* not-between
* reversed-between
* reversed-not-between
* intersection
* not-intersection
* regex-match
* regex-not-match
* any-regex-match
* none-regex-match
* every-regex-match
* not-every-regex-match
* collection-size-equals
* collection-size-not-equals
* collection-size-less-than
* collection-size-less-than-or-equal-to
* collection-size-greater-than
* collection-size-greater-than-or-equal-to
```

I expose the following field comparison operators:
```
* equals
* not-equals
* less-than
* greater-than
* less-than-or-equal-to
* greater-than-or-equal-to
* superset-of
* not-superset-of
* subset-of
* not-subset-of
* any-of
* none-of
* reversed-any-of
* reversed-none-of
* between
* not-between
* reversed-between
* reversed-not-between
* intersection
* not-intersection
```

## Contracts
The library exposes 3 APIs
1. `execute-criterion` - expects 2 inputs `message (any map)` and a map of criteria as mentioned below
   **Message**
   ```
   {:root {:parent-level-1 {:key-1 "pl1-k1"
                        :key-2 "pl1-k2"}
        :parent-level-2 {:key-1 "pl2-k1"
                        :key-2 ["t1" "t2" "t3"]}
        :parent-level-3 {:key-1 "pl3-k1"
                        :key-2 100.90}}}
   ```
   **Criterion - Type 1**
   ```
   {:id         "A"
    :type       "generic-comparator"
    :field-name "root.parent-level-1.key-1"
    :operator   "equals"
    :value      "pl1-k1"}
   ```
   **Criterion - Type 2**
   ```
    {:id             "B"
     :type           "field-comparator"
     :field-one-name "root.parent-level-1.key-1"
     :operator       "equals"
     :field-two-name "root.parent-level-1.key-3"}
   ```
2. `execute-criteria` - expects 2 inputs `message (any map)` and an array of criteria for
   evaluation as mentioned below
   **Message**
   ```
   {:root {:parent-level-1 {:key-1 "pl1-k1"
                        :key-2 "pl1-k2"}
        :parent-level-2 {:key-1 "pl2-k1"
                        :key-2 ["t1" "t2" "t3"]}
        :parent-level-3 {:key-1 "pl3-k1"
                        :key-2 100.90}}}
   ```
   **Criteria**
   ```
    [{:id        "A"
     :type       "generic-comparator"
     :field-name "root.parent-level-1.key-1"
     :operator   "equals"
     :value      "pl1-k1"}
    {:id             "B"
     :type           "field-comparator"
     :field-one-name "root.parent-level-1.key-1"
     :operator       "equals"
     :field-two-name "root.parent-level-1.key-3"}]
   ```

3. `execute-criteria` - expects 3 inputs `message (any map)`, an array of criteria for
   evaluation as mentioned below, and a logical expression string which groups the logical
   evaluation of each criterion
   **Message**
   ```
   {:root {:parent-level-1 {:key-1 "pl1-k1"
                        :key-2 "pl1-k2"}
        :parent-level-2 {:key-1 "pl2-k1"
                        :key-2 ["t1" "t2" "t3"]}
        :parent-level-3 {:key-1 "pl3-k1"
                        :key-2 100.90}}}
   ```
   **Criteria**
   ```
    [{:id       "A"
     :type       "generic-comparator"
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
     :field-two-name "root.parent-level-1.key-3"}]
   ```
   **Logical Expression**
    ```
    (and (or {{A}} {{B}}) {{C}})
    ```
    ```
    (or (and {{A}} {{B}}) {{C}})
    ```
    ```
    (or (and {{A}} {{C}}) {{B}})
    ```