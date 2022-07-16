package ru.job4j.cars.repository.api;

import java.util.List;

public interface Store<T> {

    T add(T entity);

    boolean replace(long id, T entity);

    boolean delete(long id);

    List<T> findAll();

    T findById(long id);
}

