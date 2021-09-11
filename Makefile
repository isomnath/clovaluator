all: clean tests-with-coverage compile

EXCLUDED_NAMESPACES=clovaluator.logger.log

clean:
	@echo "cleaning repository caches"
	@lein clean

tests: clean
	@echo "running tests"
	@lein test

tests-with-coverage: clean
	@echo "running tests with coverage"
	@lein cloverage -e $(EXCLUDED_NAMESPACES)

compile: clean
	@echo "building artifact"
	@lein uberjar
