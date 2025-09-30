
Global: To apply a fixed or random delay to all responses, you need to send JSON like this to the "/__admin/settings URL"



Fixed
-----
curl -X POST http://localhost:8080/__admin/settings \
  -H "Content-Type: application/json" \
  -d '{ "fixedDelay": 5000 }'


Random
------
curl -X POST http://localhost:8080/__admin/settings \
  -H "Content-Type: application/json" \
  -d '{ "delayDistribution": { "type": "lognormal", "median": 90, "sigma": 0.1 }}'

