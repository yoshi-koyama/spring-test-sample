package com.raisetech.springtestsample.service;

import com.raisetech.springtestsample.repository.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    void saveUser(String name);
}
