package com.raisetech.springtestsample.repository.mapper.sqlannotation;

import com.raisetech.springtestsample.repository.entity.User;
import com.raisetech.springtestsample.repository.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserMapperTest {

  @Autowired
  UserMapper userMapper;

  @Autowired
  DataSource dataSource;

  @Test
  @Transactional
  @Sql(scripts = {"classpath:/sqlannotation/delete-users.sql", "classpath:/sqlannotation/insert-users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
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
  @Sql(scripts = {"classpath:/sqlannotation/delete-users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Transactional
  void ユーザーの登録ができること() {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    int before = JdbcTestUtils.countRowsInTable(jdbcTemplate, "users");
    assertThat(before).isEqualTo(0);
    User user = new User("小山");
    userMapper.save(user);

    int after = JdbcTestUtils.countRowsInTable(jdbcTemplate, "users");
    assertThat(after).isEqualTo(1);

    List<Map<String, Object>> actual = jdbcTemplate.queryForList(String.format("SELECT * FROM users WHERE id = %s", user.getId()));
    assertThat(actual).hasSize(1).extracting(
        data -> Math.toIntExact((long) data.get("id")),
        data -> String.valueOf(data.get("name"))
    ).containsExactlyInAnyOrder(
        tuple(user.getId(), "小山")
    );
  }
}

