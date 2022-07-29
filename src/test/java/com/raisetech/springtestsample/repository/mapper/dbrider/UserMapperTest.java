package com.raisetech.springtestsample.repository.mapper.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.raisetech.springtestsample.repository.entity.User;
import com.raisetech.springtestsample.repository.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserMapperTest {

  @Autowired
  UserMapper userMapper;

  @Test
  @DataSet(value = "users.yml")
  @Transactional
  void すべてのユーザーが取得できること() {
    List<User> users = userMapper.findAll();
    assertThat(users)
        .hasSize(3)
        .contains(
            new User(1, "清水"),
            new User(2, "小山"),
            new User(3, "田中")
        );
  }

  @Test
  @DataSet(value = "empty.yml")
  @ExpectedDataSet(value = "expectedAfterInsert.yml", ignoreCols = "id")
  @Transactional
  void ユーザーの登録ができること() {
    User user = new User("小山");
    userMapper.save(user);
  }
}

