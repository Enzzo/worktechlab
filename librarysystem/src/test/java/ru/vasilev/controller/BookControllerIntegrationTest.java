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
class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private AuthorDAO authorRepo;

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