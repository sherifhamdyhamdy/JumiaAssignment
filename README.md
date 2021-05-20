# Jumia Country Code Validation


#### BackEnd
Spring Boot Application to retrieve data from SQLite 3 and apply filter(Country/State) and pagination

Code Cavarge is more than 90%
![Alt text](Jacoco_Report.PNG?raw=true "Title")

#### FrontEnd
Angular application to list all customers that retroved from backend 
## Getting Started

### BackEnd
* open the application folder (Jumia)
* open terminal on the application folder (Jumia)
* run the following commands:

```
mvn clean package

docker build -t jumia .

docker run -d -p 8080:8080 -t jumia 
```

### FrontEnd
* open the application folder (JumiaUI)
* open terminal on the application folder (JumiaUI)
* run the following commands:

```
ng build --prod --aot
docker build -t jumia-ui .
docker run -d -p 80:80 jumia-ui 
```
* navigate to http://localhost/

### Prerequisites

#### BackEnd
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

#### FrontEnd
* Node v14
* Angular v11


## Authors

* **Sherif Hamdy**




