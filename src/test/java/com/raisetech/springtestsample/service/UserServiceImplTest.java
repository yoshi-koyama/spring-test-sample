package com.raisetech.springtestsample.service;

import com.raisetech.springtestsample.repository.entity.User;
import com.raisetech.springtestsample.repository.mapper.UserMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserMapper userMapper;

    @Test
    void UserMapperから取得したユーザーをそのまま返すこと() {
        List<User> users = Arrays.asList(new User(1, "みかんさん"), new User(2, "ぶどうさん"), new User(3, "なしさん"));
        doReturn(users).when(userMapper).findAll();
        List<User> actualUsers = userService.findAllUsers();
        Assertions.assertThat(actualUsers).hasSize(3).isEqualTo(users);

        verify(userMapper).findAll();
    }


}
