package ru.job4j.cars.service.api;

import java.util.List;

public interface Service<T> {

    T add(T entity);

    boolean replace(long id, T entity);

    boolean delete(long id);

    List<T> findAll();

    T findById(long id);
}
