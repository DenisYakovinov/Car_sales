package ru.job4j.cars.persistance.api;

import ru.job4j.cars.model.CarBodyType;
import ru.job4j.cars.model.CarModel;

import java.util.List;

public interface CarBodyTypeStore extends Store<CarBodyType> {

    List<CarBodyType> findAllByCarModel(CarModel model);
}
