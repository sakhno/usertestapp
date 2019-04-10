
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
sudo docker build -t usertestapp:latest .
sudo docker run -d -p 8080:8080 usertestapp
```

