package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.CarBrand;
import ru.job4j.cars.persistance.api.CarBrandStore;
import ru.job4j.cars.service.api.CarBrandService;

import java.util.List;

@Service
public class CarBrandServiceImpl implements CarBrandService {

    private final CarBrandStore carBrandStore;

    public CarBrandServiceImpl(CarBrandStore carBrandStore) {
        this.carBrandStore = carBrandStore;
    }

    @Override
    public CarBrand add(CarBrand carBrand) {
        return carBrandStore.add(carBrand);
    }

    @Override
    public boolean replace(int id, CarBrand carBrand) {
        return carBrandStore.replace(id, carBrand);
    }

    @Override
    public boolean delete(int id) {
        return carBrandStore.delete(id);
    }

    @Override
    public List<CarBrand> findAll() {
        return carBrandStore.findAll();
    }

    @Override
    public CarBrand findById(int id) {
        return carBrandStore.findById(id);
    }
}
