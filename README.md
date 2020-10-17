# Hinernate-OptimisticLock
Demo Project to demonstrate Hibernate Optimistic Lock with Spring Boot

## Controller Endpoints

Availale endpoint for this project are:

* `/{accountId}`
* `/{accountId}/fast`
* `/{accountId}/slow`

## How to test The Lock

https://github.com/T4puSD/Hinernate-OptimisticLock/blob/7ed560be2de2e4bcd043dc16af8eac77b3e23dd8/src/main/java/com/tapusd/testoptimistic/controller/TestOptimisticController.java#L45-L49

Remove the try and catch from this part of code and then:
* Call the slow method with account id
* then Call the fast method with the same account id   

After 10 second the slow method will throw StaleObjectStateException because of version mismatch.
