package ru.job4j.cars.persistance.api;

import ru.job4j.cars.model.User;

import java.util.Optional;

public interface UserStore extends Store<User> {
    public Optional<User> findUserByEmailAndPwd(String email, String password);
}
