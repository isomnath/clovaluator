(ns clovaluator.comparators.threshold.operator-map
  (:require [clovaluator.comparators.operators :as ops]))

(def operators-map
  {"equals"                                   ops/equals
   "not-equals"                               ops/not-equals
   "less-than"                                ops/less-than
   "less-than-any"                            ops/less-than-any
   "less-than-none"                           ops/less-than-none
   "less-than-every"                          ops/less-than-every
   "any-less-than"                            ops/any-less-than
   "none-less-than"                           ops/none-less-than
   "every-less-than"                          ops/every-less-than
   "greater-than"                             ops/greater-than
   "greater-than-any"                         ops/greater-than-any
   "greater-than-none"                        ops/greater-than-none
   "greater-than-every"                       ops/greater-than-every
   "any-greater-than"                         ops/any-greater-than
   "none-greater-than"                        ops/none-greater-than
   "every-greater-than"                       ops/every-greater-than
   "less-than-or-equal-to"                    ops/less-than-or-equal-to
   "less-than-or-equal-to-any"                ops/less-than-or-equal-to-any
   "less-than-or-equal-to-none"               ops/less-than-or-equal-to-none
   "less-than-or-equal-to-every"              ops/less-than-or-equal-to-every
   "any-less-than-or-equal-to"                ops/any-less-than-or-equal-to
   "none-less-than-or-equal-to"               ops/none-less-than-or-equal-to
   "every-less-than-or-equal-to"              ops/every-less-than-or-equal-to
   "greater-than-or-equal-to"                 ops/greater-than-or-equal-to
   "greater-than-or-equal-to-any"             ops/greater-than-or-equal-to-any
   "greater-than-or-equal-to-none"            ops/greater-than-or-equal-to-none
   "greater-than-or-equal-to-every"           ops/greater-than-or-equal-to-every
   "any-greater-than-or-equal-to"             ops/any-greater-than-or-equal-to
   "none-greater-than-or-equal-to"            ops/none-greater-than-or-equal-to
   "every-greater-than-or-equal-to"           ops/every-greater-than-or-equal-to
   "superset-of"                              ops/superset-of
   "not-superset-of"                          ops/not-superset-of
   "subset-of"                                ops/subset-of
   "not-subset-of"                            ops/not-subset-of
   "any-of"                                   ops/any-of
   "none-of"                                  ops/none-of
   "reversed-any-of"                          ops/reversed-any-of
   "reversed-none-of"                         ops/reversed-none-of
   "between"                                  ops/between
   "not-between"                              ops/not-between
   "reversed-between"                         ops/reversed-between
   "reversed-not-between"                     ops/reversed-not-between
   "intersection"                             ops/intersection
   "not-intersection"                         ops/not-intersection
   "regex-match"                              ops/regex-match
   "regex-not-match"                          ops/regex-not-match
   "any-regex-match"                          ops/any-regex-match
   "none-regex-match"                         ops/none-regex-match
   "every-regex-match"                        ops/every-regex-match
   "not-every-regex-match"                    ops/not-every-regex-match
   "collection-size-equals"                   ops/collection-size-equals
   "collection-size-not-equals"               ops/collection-size-not-equals
   "collection-size-less-than"                ops/collection-size-less-than
   "collection-size-less-than-or-equal-to"    ops/collection-size-less-than-or-equal-to
   "collection-size-greater-than"             ops/collection-size-greater-than
   "collection-size-greater-than-or-equal-to" ops/collection-size-greater-than-or-equal-to})