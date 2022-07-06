package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.persistance.api.PhotoStore;
import ru.job4j.cars.service.api.PhotoService;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoStore photoStore;

    public PhotoServiceImpl(PhotoStore photoStore) {
        this.photoStore = photoStore;
    }

    @Override
    public Photo add(Photo photo) {
        return photoStore.add(photo);
    }

    @Override
    public boolean replace(int id, Photo photo) {
        return photoStore.replace(id, photo);
    }

    @Override
    public boolean delete(int id) {
        return photoStore.delete(id);
    }

    @Override
    public List<Photo> findAll() {
        return photoStore.findAll();
    }

    @Override
    public Photo findById(int id) {
        return photoStore.findById(id);
    }
}
