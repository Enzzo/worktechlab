package ru.vasilev.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import ru.vasilev.model.Author;
import ru.vasilev.repository.AuthorRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthorRepository authorRepo;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAllAuthors_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/v1/authors"))
               .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createAuthor_ShouldReturnCreatedAuthor() throws Exception {
        Author author = new Author("New Author", null);
        String json = new ObjectMapper().writeValueAsString(author);

        mockMvc.perform(post("/api/v1/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Author"));
    }
}