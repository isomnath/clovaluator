(ns clovaluator.comparators.field.operator-map
  (:require [clovaluator.comparators.operators :as ops]))

(def operators-map
  {"equals"                   ops/equals
   "not-equals"               ops/not-equals
   "less-than"                ops/less-than
   "greater-than"             ops/greater-than
   "less-than-or-equal-to"    ops/less-than-or-equal-to
   "greater-than-or-equal-to" ops/greater-than-or-equal-to
   "superset-of"              ops/superset-of
   "not-superset-of"          ops/not-superset-of
   "subset-of"                ops/subset-of
   "not-subset-of"            ops/not-subset-of
   "any-of"                   ops/any-of
   "none-of"                  ops/none-of
   "reversed-any-of"          ops/reversed-any-of
   "reversed-none-of"         ops/reversed-none-of
   "between"                  ops/between
   "not-between"              ops/not-between
   "reversed-between"         ops/reversed-between
   "reversed-not-between"     ops/reversed-not-between
   "intersection"             ops/intersection
   "not-intersection"         ops/not-intersection})