package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.persistance.api.CarStore;
import ru.job4j.cars.service.api.CarService;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarStore carStore;

    public CarServiceImpl(CarStore carStore) {
        this.carStore = carStore;
    }

    @Override
    public Car add(Car car) {
        return carStore.add(car);
    }

    @Override
    public boolean replace(int id, Car car) {
        return carStore.replace(id, car);
    }

    @Override
    public boolean delete(int id) {
        return carStore.delete(id);
    }

    @Override
    public List<Car> findAll() {
        return carStore.findAll();
    }

    @Override
    public Car findById(int id) {
        return carStore.findById(id);
    }

    @Override
    public Car replaceWithDrivers(Car car) {
        return carStore.replaceWithDrivers(car);
    }
}
