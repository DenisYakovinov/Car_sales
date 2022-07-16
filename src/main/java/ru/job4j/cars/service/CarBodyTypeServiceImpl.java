package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.CarBodyType;
import ru.job4j.cars.repository.api.CarBodyTypeStore;
import ru.job4j.cars.service.api.CarBodyTypeService;

import java.util.List;

@Service
public class CarBodyTypeServiceImpl implements CarBodyTypeService {

    private final CarBodyTypeStore carBodyTypeStore;

    public CarBodyTypeServiceImpl(CarBodyTypeStore carBodyTypeStore) {
        this.carBodyTypeStore = carBodyTypeStore;
    }

    @Override
    public CarBodyType add(CarBodyType carBodyType) {
        return carBodyTypeStore.add(carBodyType);
    }

    @Override
    public boolean replace(long id, CarBodyType carBodyType) {
        return carBodyTypeStore.replace(id, carBodyType);
    }

    @Override
    public boolean delete(long id) {
        return carBodyTypeStore.delete(id);
    }

    @Override
    public List<CarBodyType> findAll() {
        return carBodyTypeStore.findAll();
    }

    @Override
    public CarBodyType findById(long id) {
        return carBodyTypeStore.findById(id);
    }
}
