package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.CarModel;
import ru.job4j.cars.repository.api.CarModelStore;
import ru.job4j.cars.service.api.CarModelService;

import java.util.List;

@Service
public class CarModelServiceImpl implements CarModelService {

    private final CarModelStore carModelStore;

    public CarModelServiceImpl(CarModelStore carModelStore) {
        this.carModelStore = carModelStore;
    }

    @Override
    public CarModel add(CarModel carModel) {
        return carModelStore.add(carModel);
    }

    @Override
    public boolean replace(long id, CarModel carModel) {
        return carModelStore.replace(id, carModel);
    }

    @Override
    public boolean delete(long id) {
        return carModelStore.delete(id);
    }

    @Override
    public List<CarModel> findAll() {
        return carModelStore.findAll();
    }

    @Override
    public CarModel findById(long id) {
        return carModelStore.findById(id);
    }

    @Override
    public CarModel replaceWithEngines(CarModel carModel) {
        return carModelStore.replaceWithEngines(carModel);
    }

    @Override
    public List<CarModel> findAllByBrand(long brandId) {
        return carModelStore.findAllByBrand(brandId);
    }
}
