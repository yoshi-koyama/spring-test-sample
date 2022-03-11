package com.raisetech.springtestsample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class SpringTestSampleApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	void contextLoads() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/users")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("user"));
	}

}
