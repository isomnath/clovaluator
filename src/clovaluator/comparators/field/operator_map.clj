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
   "any-of"                   ops/any-of
   "none-of"                  ops/none-of
   "intersection"             ops/intersection
   "not-intersection"         ops/not-intersection})