package ru.job4j.cars.service.api;

import ru.job4j.cars.model.Advertisement;

import java.util.List;

public interface AdvertisementService extends Service<Advertisement> {

    List<Advertisement> findAllWithPhotos();

    boolean setSoldById(long id);

    Advertisement replaceWithPhotos(Advertisement advertisement);
}
