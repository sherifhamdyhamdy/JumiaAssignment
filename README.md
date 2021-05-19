# Jumia Country Code Validation
This project contains of two application one for frontend and second for backend


#### BackEnd:
Spring Boot Application to retrieve data from SQLite 3 and apply filter(Country/State) and pagination

#### FrontEnd:
Angular application to list all users with state of phone number of each user, Application can filter by country, state and pagination feature is allowed 


## Getting Started

###BackEnd
* open the application folder (Jumia)
* open terminal on the application folder (Jumia)
* run the following commands:

```
mvn clean package

docker build -t jumia .

docker run -d -p 8080:8080 -t jumia 
```

###FrontEnd
* open the application folder (JumiaUI)
* open terminal on the application folder (JumiaUI)
* run the following commands:

```
docker build -t jumia-ui .
docker run -d -p 80:80 jumia-ui 
```
* navigate to http://localhost/

### Prerequisites

####BackEnd
* Java 11
* Spring Boot
* SQlite 
* Jacoco
* JUnit5
* Lombok
* MapStruct
* Mockito
* Docker
* SonarLint

####FrontEnd
* Node v14
* Angular v11


## Authors

* **Sherif Hamdy**




