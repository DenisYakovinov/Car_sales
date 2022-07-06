package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Advertisement;
import ru.job4j.cars.persistance.api.AdvertisementStore;
import ru.job4j.cars.service.api.AdvertisementService;

import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementStore advertisementStore;

    public AdvertisementServiceImpl(AdvertisementStore advertisementStore) {
        this.advertisementStore = advertisementStore;
    }

    @Override
    public Advertisement add(Advertisement advertisement) {
        return advertisementStore.add(advertisement);
    }

    @Override
    public boolean replace(int id, Advertisement advertisement) {
        return advertisementStore.replace(id, advertisement);
    }

    @Override
    public boolean delete(int id) {
        return advertisementStore.delete(id);
    }

    @Override
    public List<Advertisement> findAll() {
        return advertisementStore.findAll();
    }

    @Override
    public Advertisement findById(int id) {
        return advertisementStore.findById(id);
    }
}
