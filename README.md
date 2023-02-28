# Spring Boot OAuth2 JWT Demo

Our reference minimum implementations for using OAuth2 Authorization Server and Resource Server with Spring Boot

## Postman Collection:
https://api.postman.com/collections/1242165-56019d06-d619-417a-afca-c6a3c8ebf6ca?access_key=PMAT-01GTC31CZCV8YNG2XEAAY9VRE6

## Postman Environment:

```
ashost: http://localhost:8081
rshost: http://localhost:8081
client-id: myclientid
client-secret: myclientsecret
username: alex@gmail.com
password: 123456
token:
```

## How to run:

- Import Postman collection
- Setup Postman environment
- Open Spring Boot projects in your favorite IDE and run
- Tests on Postman:
  - PART 1: not logged
    - Request GET /products (should return products)
    - Request GET /products/1 (should return 401)
    - Request POST /products {"name":"Tablet"} (should return 401)
  - PART 2: logged as Alex
    - Set alex@gmail.com as username in Postman environment
    - Request login request (should return 200 Ok with JWT token. That token will be saved on 'token' environment variable)
    - Request GET /products (should return products)
    - Request GET /products/1 (should return product)
    - Request POST /products {"name":"Tablet"} (should return 403) 
  - PART 3: logged as Maria
    - Set maria@gmail.com as username in Postman environment
    - Request login request (should return 200 Ok with JWT token. That token will be saved on 'token' environment variable)
    - Request GET /products (should return products)
    - Request GET /products/1 (should return product)
    - Request POST /products {"name":"Tablet"} (should insert product) 

