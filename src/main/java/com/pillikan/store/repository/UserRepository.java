package com.pillikan.store.repository;

import com.pillikan.store.model.User;

import java.util.List;

public interface UserRepository {
    User getById(int id);

    List<User> getAll();

    User getByEmail(String email);

    User create(User user);

    boolean delete(int id);
}
