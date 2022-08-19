# Spring Boot OAuth2 JWT

Our reference minimum implementations for using OAuth2 Authorization Server and Resource Server with Spring Boot

## Postman Collection:
https://www.getpostman.com/collections/4d6be99fce598512a1bc

## Postman Environment:

```
host: http://localhost:8080
client-id: myclientid
client-secret: myclientsecret
username: maria@gmail.com
password: 123456
token:
```

## How to run:

- Import Postman collection
- Setup Postman environment
- Open Spring Boot project in your favorite IDE and run
- Tests on Postman:
  - Request Hello endpoint (should return 401 Unauthorized)
  - Request Login endpoing (should return 200 Ok with JWT token. That token will be saved on 'token' environment variable)
  - Request Hello endpoint again (should return 200 Ok)
