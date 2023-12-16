## Description:
The application calculates the loan payment plan for a given 
type of loan with loan amount and payback years as input parameters.
Each type of loan has a different interest rate.
The application also assumes a fixed monthly payment throughout 
the loan term and does not consider additional factors 
such as taxes or fees.

## Prerequisites:
- Java
- Maven

## Use maven to build a jar file:
mvn clean install

## Run the application:
java -jar loancalculator-0.0.1-SNAPSHOT.jar

## Building and running the application with maven:
mvn clean package spring-boot:run

## Url to access the application:
http://localhost:8080/loan/{loantype}

Examples: </br>
http://localhost:8080/loan/housing </br>
http://localhost:8080/loan/car </br>
http://localhost:8080/loan/personal </br>

## Observations:
- Form resubmission is not fixed.
- Logging is not implemented.
