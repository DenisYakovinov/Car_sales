package ru.job4j.cars.persistance.api;

import ru.job4j.cars.model.CarModel;

import java.util.List;

public interface CarModelStore extends Store<CarModel> {

    CarModel replaceWithEngines(CarModel carModel);
    List<CarModel> findAllByBrand(long brandId);
}
