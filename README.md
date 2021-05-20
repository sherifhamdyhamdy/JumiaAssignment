# Jumia Country Code Validation

Jumia Country Code Validation is an application to validate phone number of customer.
##### Features
* Validate Phone number (Valid | Not Valid)
* Filter by 
  * Country only
  * State only
  * Country && State
* Pagination  
  
##### Sequence Diagram 

![Alt text](sequence_diagram.png?raw=true "Title")
## BackEnd
Spring Boot Application to retrieve data from SQLite 3 and apply filter (Country/State) and pagination.The number of displayed rows was configurable in properties file.

Code Converge  is more than 90%
![Alt text](Jacoco_Report.PNG?raw=true "Title")

### Getting Started
* open the application folder (Jumia)
* open terminal on the application folder (Jumia)
* run the following commands:

```
mvn clean install package

docker build -t jumia .

docker run -d -p 8080:8080 -t jumia 
```
### CURLs
#### Without Filter
```
curl --location --request GET 'http://localhost:8080/customers'
```
#### With Filter
```
curl --location --request GET 'http://localhost:8080/customers?country=Morocco&state=valid'
```
### With Pagination
```
curl --location --request GET 'http://localhost:8080/customers?page=4'
```
### Technologies

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


## FrontEnd
Angular application to list all customers that retrieved from backend 
### Getting Started
* open the application folder (JumiaUI)
* open terminal on the application folder (JumiaUI)
* run the following commands:

```
npm install
ng build --prod --aot
docker build -t jumia-ui .
docker run -d -p 80:80 jumia-ui 
```
* navigate to http://localhost/

### Technologies
* Node v14
* Angular v11


## Authors

* **Sherif Hamdy**




