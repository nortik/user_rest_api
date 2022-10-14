# user_rest_api : this app permit the management of users.
user_rest_api

To start to use this app you need to download the code, brings all the dependency with maven.
Also, you can change the name and password in application.properties: 
-spring.datasource.username=xxx
-spring.datasource.password=xxx

To add a new user you can use Postman or CURL with this json:

curl --location --request POST 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "mati",
    "email":"mati@mat.com",
    "password":"AAAaaa222",
    "phones": [
        { 
            "number": "11212121",
            "citycode": "22",
            "countrycode": "54"
        }
    ]
}'

Observation: to change emailRegex and password Regex you can do it in: config.properties 

FYI:java 8, Spring Boot, H2
