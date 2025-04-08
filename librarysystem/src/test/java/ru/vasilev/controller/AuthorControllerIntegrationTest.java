package ru.vasilev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.transaction.Transactional;
import ru.vasilev.dao.AuthorDAO;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthorDAO authorRepo;

//    @Test
//    @WithMockUser(username = "admin", roles = {"ADMIN"})
//    void getAllAuthors_ShouldReturnOk() throws Exception {
//        mockMvc.perform(get("/api/v1/authors"))
//               .andExpect(status().isOk());
//    }
//
//    @Test
//    @WithMockUser(username = "admin", roles = {"ADMIN"})
//    void createAuthor_ShouldReturnCreatedAuthor() throws Exception {
//        Author author = new Author("New Author", null);
//        String json = new ObjectMapper().writeValueAsString(author);
//
//        mockMvc.perform(post("/api/v1/authors")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("New Author"));
//    }
}