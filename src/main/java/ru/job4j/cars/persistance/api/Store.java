package ru.job4j.cars.persistance.api;

import java.util.List;

public interface Store<T> {

    T add(T entity);

    boolean replace(int id, T entity);

    boolean delete(int id);

    List<T> findAll();

    T findById(int id);
}

