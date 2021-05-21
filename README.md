# security-context-metric-tags

How to run

```shell
./mvnw spring-boot:run
```

Invoke endpoint to create metrics with the desired tags

```shell
curl --user user:password localhost:8080/hello
```

Check available tags with their values by running

```shell
curl --user user:password http://localhost:8080/actuator/metrics/http.server.requests
```

You should see a similar response to

```json
{
  "name": "http.server.requests",
  "description": null,
  "baseUnit": "seconds",
  "measurements": [
    {
      "statistic": "COUNT",
      "value": 11.0
    },
    {
      "statistic": "TOTAL_TIME",
      "value": 1.038728184
    },
    {
      "statistic": "MAX",
      "value": 0.102460578
    }
  ],
  "availableTags": [
    {
      "tag": "exception",
      "values": [
        "None"
      ]
    },
    {
      "tag": "principleName",
      "values": [
        "user"
      ]
    },
    {
      "tag": "method",
      "values": [
        "POST",
        "GET"
      ]
    },
    {
      "tag": "uri",
      "values": [
        "/actuator/metrics/{requiredMetricName}",
        "REDIRECTION"
      ]
    },
    {
      "tag": "outcome",
      "values": [
        "CLIENT_ERROR",
        "REDIRECTION",
        "SUCCESS"
      ]
    },
    {
      "tag": "status",
      "values": [
        "404",
        "200",
        "400",
        "302"
      ]
    }
  ]
}
```