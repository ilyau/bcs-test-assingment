Application for calculating the current value of the stock portfolio and getting other useful info.

## Usage

REST-query to http://localhost:8080/stock-portfolio/analyze
with array of stocks

### Example
```
curl -X POST \
  http://localhost:8080/stock-portfolio/analyze \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: b98fc3bb-f3dd-49bf-a52d-f9ad9a20d581' \
  -H 'cache-control: no-cache' \
  -d '{
    "stocks": [
        {
            "symbol": "AAPL",
            "volume": 50
        },
        {
            "symbol": "HOG",
            "volume": 10
        },
        {
            "symbol": "MDSO",
            "volume": 1
        },
        {
            "symbol": "IDRA",
            "volume": 1
        },
        {
            "symbol": "MRSN",
            "volume": 1
        }
    ]
}'
```

### Response example

```
{
    "value": 9112.02,
    "allocations": [
        {
            "sector": "Technology",
            "assetValue": 8732.52,
            "proportion": 0.958
        },
        {
            "sector": "Consumer Cyclical",
            "assetValue": 371.2,
            "proportion": 0.041
        },
        {
            "sector": "Healthcare",
            "assetValue": 8.3,
            "proportion": 0.001
        }
    ]
}
```


## Install instructions

1. Be sure you have installed:

    - Docker (https://docker.com)
    - Maven (https://maven.apache.org/)
    - Git (https://git-scm.com/)

2. Get sources

    ``` 
    git clone https://github.com/ilyau/bcs-test-assingment
    ```

3. Move into dir with project

4. Run tests

    ```
    mvn test
    ```

5. Build docker image

    ```
    mvn install dockerfile:build
    ```

6. Run application

    ```
    docker run -e "SPRING_PROFILES_ACTIVE=prod" -p 8080:8080 -t bcs/stock-portfolio
    ```