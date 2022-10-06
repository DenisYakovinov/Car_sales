FROM maven:3.8-openjdk-17 as maven
WORKDIR cars
COPY . /cars
RUN mvn install

FROM openjdk:17.0.2-jdk
WORKDIR cars
COPY --from=maven /cars/target/job4j_cars-1.0-SNAPSHOT.jar cars_app.jar
CMD java -jar cars_app.jar