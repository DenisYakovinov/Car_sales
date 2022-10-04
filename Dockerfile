FROM openjdk
WORKDIR cars
ADD target/job4j_cars-1.0-SNAPSHOT.jar cars_app.jar
ENTRYPOINT java -jar cars_app.jar