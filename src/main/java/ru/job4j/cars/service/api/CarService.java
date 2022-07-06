package ru.job4j.cars.service.api;

import ru.job4j.cars.model.Car;

public interface CarService extends Service<Car> {

    public Car replaceWithDrivers(Car car);
}
