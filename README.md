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

3) Inter Process Communications(hr.*)
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

4) Data Transaction across services
  - Database Log Tailing
    * addition, updation and deletion changes are published.(change data capture)
    * Transaction log mining(Debezium + apache)(section 8, 24), reads logs directly from databases(psql, mongodb, casandra)(hr.performance-management & hr-employee movement)
    * Alternative is to use Polling publisher(section  8, 27) using postman runner at 5:00
  - Distributed transactions across microservices
   * implement using api calls sequentially A -> B -> C -> A or make one service a point of center A->B, A->C
     Pros: Easy straight forward
     cons: 
        -> All nodes must be available
        -> we cannot rollback microservice transactions, records will be created
        -> can cause inconsistent data/information
   * Saga pattern(section 8, 28)
      -> Asyc communication via message broker
      -> Sequence of local transactions
      -> Each local transaction update then publish
      -> Saga coordination approach
        - Choreography A -> B -> C -> A
        - Orchestration A->B, A->C
      -> Pro: 
        - usually there is a pending/progress status
        - consistent data
        - Loose couple
      -> Cons: 
        - implementation harder
      -> Saga Error Handling(commit & rollback)
        - we cannot rollback in saga pattern
        - need to handle compensation. If bank debited and error occurs, then credit has to be done
5) Querying data from multiple services
  - Database per service will be there
  - Views can be used to show data from different database for reports(section 9, 35, 2:00)
  - Api Composition, get data using apis and combine.
  - CQRS(Command Query Responsibility Segregation)
    * a separate db will be needed, events published will be saved in here as history.
    * apis can be exposed to show reports
    * Pros: simple & fast. Not depends on database product
    * Cons: Increase complexity. Data inconsistency possible
    * Good to use log miner like debezium to track changes. If dev forgets to publish events data inconsistency will be there
  - Large Data, use pagination