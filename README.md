[![Java CI with Maven](https://github.com/DenisYakovinov/job4j_cars/actions/workflows/maven.yml/badge.svg)](https://github.com/DenisYakovinov/job4j_cars/actions/workflows/maven.yml)
# Spring Boot app
<h2>Used car sales service</h2>

<h2>Technologies</h2>
<ul>
    <li>Spring Boot</li>
    <li>Spring MVC</li>
    <li>Bootstrap</li>
    <li>Thymeleaf</li>
    <li>Hibernate</li>
    <li>Postgres</li>
    <Li>liquibase</Li>
</ul>

### 1. Main page. List of all advertisements.
![main](images_examples/main.png)
### 2. The page for adding a new advertisement (authenticated users)
###   2.1 first, user should select a brand (from existing data)
![SelectBrand](images_examples/selectBrand.png)
###   2.2 next, user need to choose a model of the car available for the brand
![selectCarModel](images_examples/selectCarModel.png)
###   2.3 next, select a car body type 
![carBodytype](images_examples/carBodytype.png)
###   2.4 next, an engine type
![engineType](images_examples/engineType.png)
###   2.5 and all others attributes
![attributes](images_examples/attributes.png)
### 3. detailed description of the ad (the author of the ad can edit, delete or set the status sold)
![detail](images_examples/detail.png)

Running via Docker Compose

1. clone the project
```
https://github.com/DenisYakovinov/job4j_cars.git
```
2. go to the project root:
```
cd job4j_cars
```
3. then build the application using maven:
```
mvn install
```
4. build docker image
```
docker build -t cars .
```
5. and run db and app
```
docker-compose up 
```
6. then move to localhost/index
![docker_compose](images_examples/docker_compose.png)
![localhost](images_examples/localhost.png)