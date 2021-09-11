COVERALLS_URL='https://coveralls.io/api/v1/jobs'
CLOVERAGE_VERSION='1.2.2-SNAPSHOT' lein2 cloverage -o cov --coveralls
curl -F 'json_file=@cov/coveralls.json' "$COVERALLS_URL"