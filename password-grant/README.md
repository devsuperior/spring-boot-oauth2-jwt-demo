# Spring Boot OAuth2 JWT Demo (password grant type)

Our reference minimum implementations for using OAuth2 Authorization Server and Resource Server with Spring Boot, using password grant type, and user/roles from a database.

## Postman Collection and Environment:
Download them from this folder.

## How to run:

- Import Postman collection and environment
  - Note: the Login request path may be `/oauth2/token` or `/oauth/token` depending on Spring Boot version. Change request path if needed.
  - Note: the `ashost` environment variable must be set to `http://localhost:8080` if you're running a project that contains both Authorization Server and Resource Server. 

- Open Spring Boot project(s) in your favorite IDE and run

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
