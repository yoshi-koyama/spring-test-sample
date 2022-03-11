package com.raisetech.springtestsample.repository.mapper;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import com.raisetech.springtestsample.repository.entity.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Propagation;
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
    @DataSet(value = "users.yml", cleanBefore = true)
    void すべてのユーザーが取得できること() {
        List<User> users = userMapper.findAll();
        assertThat(users).hasSize(2);
    }

    @Test
    @DataSet(value = "empty.yml")
    @ExpectedDataSet(value = "expectedAfterInsert.yml", ignoreCols = "id")
    @Transactional(propagation = Propagation.SUPPORTS)
    void ユーザーの登録ができること() {
        userMapper.save("清水");
        List<User> users = userMapper.findAll();
        assertThat(users).hasSize(1);
    }
}

