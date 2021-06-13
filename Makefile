all: clean tests-with-coverage build

TEST_ENVIRONMENT_NAME="test"
TEST_LOG_LEVEL="debug"
EXCLUDED_NAMESPACES=clovaluator.logger.log

clean:
	@echo "cleaning repository caches"
	@lein clean

tests:
	@echo "running tests"
	@export ENVIRONMENT=$(TEST_ENVIRONMENT_NAME) && lein test

tests-with-coverage: set-test-log-level
	@echo "running tests with coverage"
	@export ENVIRONMENT=$(TEST_ENVIRONMENT_NAME) && lein cloverage -e $(EXCLUDED_NAMESPACES)

build:
	@echo "building artifact"
	@lein uberjar

set-test-log-level:
	@echo "set log level for testing"
	@export LOG_LEVEL=$(TEST_LOG_LEVEL)