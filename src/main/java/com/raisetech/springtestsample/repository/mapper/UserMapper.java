package com.raisetech.springtestsample.repository.mapper;

import com.raisetech.springtestsample.repository.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

  @Select("SELECT * FROM users")
  List<User> findAll();

  @Insert("INSERT INTO users (name) value (#{name})")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void save(User user);

}
