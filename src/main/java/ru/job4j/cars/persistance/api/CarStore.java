package ru.job4j.cars.persistance.api;

import ru.job4j.cars.model.Car;

public interface CarStore extends Store<Car> {

    public Car replaceWithDrivers(Car car);
}
