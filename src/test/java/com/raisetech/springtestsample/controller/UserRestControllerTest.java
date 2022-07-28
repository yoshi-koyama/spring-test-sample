package com.raisetech.springtestsample.controller;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

@SpringBootTest
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRestControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  @DataSet(value = "users.yml")
  void ユーザーが取得できること() throws Exception {
    String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/users"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
    JSONAssert.assertEquals("[" +
        "  {" +
        "    \"id\": 1," +
        "    \"name\": \"清水\"" +
        "  }," +
        "  {" +
        "    \"id\": 2," +
        "    \"name\": \"小山\"" +
        "  }," +
        "  {" +
        "    \"id\": 3," +
        "    \"name\": \"田中\"" +
        "  }" +
        "]", response, JSONCompareMode.STRICT);
  }

  @Test
  @DataSet(value = "empty.yml")
  @ExpectedDataSet(value = "expectedAfterInsert.yml", ignoreCols = "id")
  void ユーザーが登録できること() throws Exception {
    String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/users").contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"小山\"}"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
    JSONAssert.assertEquals("{\"message\":\"user created\"}", response, JSONCompareMode.STRICT);
  }
}
