package ru.job4j.cars.service.api;

import ru.job4j.cars.model.User;

import java.util.Optional;

public interface UserService extends Service<User> {
    public Optional<User> findUserByEmailAndPwd(String email, String password);
}

