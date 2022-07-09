package ru.job4j.cars.service.api;

import ru.job4j.cars.model.CarModel;

import java.util.List;

public interface CarModelService extends Service<CarModel> {

    CarModel replaceWithEngines(CarModel carModel);

    List<CarModel> findAllByBrand(long brandId);
}
