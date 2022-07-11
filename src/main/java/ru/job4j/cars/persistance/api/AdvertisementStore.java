package ru.job4j.cars.persistance.api;

import ru.job4j.cars.model.Advertisement;

import java.util.List;

public interface AdvertisementStore extends Store<Advertisement> {

    List<Advertisement> findAllWithPhotos();

    boolean setSoldById(long id);

    Advertisement replaceWithPhotos(Advertisement advertisement);
}
