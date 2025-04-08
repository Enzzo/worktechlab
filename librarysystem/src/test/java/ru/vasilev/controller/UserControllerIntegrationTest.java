package ru.vasilev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.transaction.Transactional;
import ru.vasilev.dao.UserDAO;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserDAO userRepo;

//	@Test
//	@WithMockUser(username = "admin", roles = { "ADMIN" })
//	void getAllUsers_ShouldReturnOk() throws Exception {
//		mockMvc.perform(get("/api/v1/users")).andExpect(status().isOk());
//	}

//	@Test
//	@WithMockUser(username = "admin", roles = { "ADMIN" })
//	void createUser_ShouldReturnCreatedUser() throws Exception {
//		User user = new User("newuser", "password");
//		String json = new ObjectMapper().writeValueAsString(user);
//
//		mockMvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content(json))
//				.andExpect(status().isOk()).andExpect(jsonPath("$.username").value("newuser"));
//	}

}