# Clovaluator

I am a Clojure Library dimension agnostic criteria comparator. 
I expose the following generic comparison operators:
```
* equals
* not-equals
* less-than
* greater-than
* less-than-or-equal-to
* greater-than-or-equal-to
* superset-of
* not-superset-of
* any-of
* none-of
* between
* not-between
* intersection
* not-intersection
* regex-match
* regex-not-match
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
* any-of
* none-of
* intersection
* not-intersection
```

## Contracts
The library exposes a function `execute-criteria` and expects 2 inputs `message (any map)` and an 
array of criteria for evaluation. 
* [Sample contract for a criteria](contracts/sample-criteria.md)
* [Sample contract for a message](contracts/sample-message.md)

## Logging Support
The library supports log4j2 based logging. Log level for the library can be controlled via
environment variable `LOG_LEVEL`. Possible values for the same are - `INFO, DEBUG, ERROR`.