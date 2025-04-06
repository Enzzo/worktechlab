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
import ru.vasilev.model.Book;
import ru.vasilev.repository.AuthorRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private AuthorRepository authorRepo;

//    @Test
//    void getAllBooks_ShouldReturnOk() throws Exception {
//        mockMvc.perform(get("/api/v1/books"))
//               .andExpect(status().isOk());
//    }

//    @Test
//    @WithMockUser(username = "admin", roles = {"ADMIN"})
//    void createBook_ShouldReturnCreatedBook() throws Exception {
//        Author author = new Author("New Author", null);
//        authorRepo.save(author);
//        Book book = new Book("New Book", author);
//        String json = new ObjectMapper().writeValueAsString(book);
//
//        mockMvc.perform(post("/api/v1/books")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value("New Book"));
//    }
}