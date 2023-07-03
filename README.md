# Microservices

1) micro-service-chassis((section-5))
  - see how config files are separated 
  - use of source sets 
  - use of exception and exception handler (9:23)
  - spring doc for documentation (23)

2) micro-service-chassis-two((section-6, 15))
  - CQS(command query service) architecture
  - Hexagonal architecture
  - usage of cqs pattern

3) Inter Process Communications
  - Synchronous(one-to-one) grpc/rest api calls
  - Asynchronous(one-to-many) pub/sub pattern
  - use open-feign for ipc(sync). Instead of writing rest code use feign and use like repo code(section7, 17)
    https://www.baeldung.com/spring-cloud-openfeign
  - use kafka for async ipc(section7, 17, 9:00)
  - Service discovery
    * Consul from hashicorp
    * Eureka from netflix spring cloud
  - Communication error handling(Circuit breaker) : sync(section7, 22)
    * server side have retry attempt, with circuit breaker
    * circuit breaker + open feign(retryer) + spring retry 
  - Communication error handling(Circuit breaker) : async(section7, 17, 8:00)
    * Dead letter topic if not delivered the msg
    * To enable retry attempt, do it kafka itself. By adding a DLQ(dead letter queue topic). This will used by the consumer
      to add msgs if there is serialization error, cannot find record using id etc
  - Circuit breaker alternative resilience4j for sync