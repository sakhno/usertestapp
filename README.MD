### Description

Adding user profile:

```
curl -d '{"name":"value1"}' -H "Accept: application/json" -H "Content-Type: application/json" -X POST http://localhost:8080/user
```
Geting user profile:

```
curl -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/user/{userId}
```

### Run with mysql

By default server starts with in memory database H2, You can start it with mysql by providing mysql config in 
application.properties file as -Dspring.config.location=/path/to/file/application.properties
```
spring.datasource.url=jdbc:mysql://localhost/usertest?serverTimezone=UTC
spring.datasource.username=usertest
spring.datasource.password=usertest
spring.jpa.hibernate.ddl-auto=update
```

### Run in docker
from project folder:
```bash
mvn clean package
sudo docker build -t usertestapp:latest .
sudo docker run -d -p 8080:8080 usertestapp
```

