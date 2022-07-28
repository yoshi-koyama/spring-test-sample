package com.raisetech.springtestsample.service;

import com.raisetech.springtestsample.repository.entity.User;
import com.raisetech.springtestsample.repository.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;

  public UserServiceImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public List<User> findAllUsers() {
    return userMapper.findAll();
  }

  @Override
  public int saveUser(String name) {
    User user = new User(name);
    userMapper.save(user);
    return user.getId();
  }
}
