package hamdan.javawebmvc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void addTodo() throws Exception {
        this.mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "Spring MVC")
                .param("description", "Spring MVC")
                .param("completed", "false")
                .param("dueDate", "2025-12-20")
        ).andExpectAll(
                status().is3xxRedirection(),
                redirectedUrl("/dashboard")
        );
    }

    @Test
    void deleteTodo() throws Exception {
        this.mockMvc.perform(delete("/todos/1").accept(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/dashboard")
                );
    }
}