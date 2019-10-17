# Spring Cloud Ribbon Demo
Demo of client side load balancing by Ribbon, using Eureka for service discovery.

## Running the Eureka Server

1. Navigate to `eureka-server` directory.
2. Execute `mvn spring-boot:run` in terminal to start Eureka server.

## Running the Microservices

1. Navigate to `alice-service` directory.
2. Open two terminals and execute below commands to start multiple instances of ALICE service.
```
mvn spring-boot:run -Dserver.port=9001
mvn spring-boot:run -Dserver.port=9002
```
3. Navigate to `bob-service` directory.
4. Open two terminals and execute below commands to start multiple instances of BOB service.
```
mvn spring-boot:run -Dserver.port=10001
mvn spring-boot:run -Dserver.port=10002
```
5. Open Eureka console at `http://localhost:8001/` and see that 2 instances of both services have been registered.
![Eureka Server](/screenshots/eureka-server.png)
6. Open browser and go to `http://localhost:9001/get-stuff/BOB` to fetch data from BOB service into ALICE service. Notice that in the response `Stuff by Bob <port>`, the port number keeps changing in round-robin fashion due to load balancing by Ribbon.
7. Open browser and go to `http://localhost:10001/get-stuff/ALICE` to fetch data from ALICE service into BOB service. Notice that in the response `Stuff by Alice <port>`, the port number keeps changing in round-robin fashion due to load balancing by Ribbon.
