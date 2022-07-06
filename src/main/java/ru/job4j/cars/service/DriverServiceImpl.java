package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.persistance.api.DriverStore;
import ru.job4j.cars.service.api.DriverService;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverStore driverStore;

    public DriverServiceImpl(DriverStore driverStore) {
        this.driverStore = driverStore;
    }

    @Override
    public Driver add(Driver driver) {
        return driverStore.add(driver);
    }

    @Override
    public boolean replace(int id, Driver driver) {
        return driverStore.replace(id, driver);
    }

    @Override
    public boolean delete(int id) {
        return driverStore.delete(id);
    }

    @Override
    public List<Driver> findAll() {
        return driverStore.findAll();
    }

    @Override
    public Driver findById(int id) {
        return driverStore.findById(id);
    }
}
