# Product Aggregator

Microservice for managing client and their products. 
REST API for creating, getting, deleting clients and products.
Contains functionality of sending products to kafka for certain period of time.

### Technologies
* Java 17
* Spring Boot 3.3
* Spring Data
* QueryDSL
* PostgreSQL
* Apache Kafka
* Spring Validation
* Spring Web
* Lombok
* Maven

### Run locally
1. First, you need a running DB - PostgreSQL 
with name=product-aggregator-db.
2. Second, you need a running Kafka on localhost:9092.
3. Finally, you need to have Maven installed locally, 
otherwise use the IntelliJ IDEA run.
```
    mvn package
    java -jar target/product-aggregator-1.0-SNAPSHOT.jar
```
4. App will be available at http://localhost:8080.