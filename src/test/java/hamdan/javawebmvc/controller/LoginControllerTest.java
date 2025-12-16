package hamdan.javawebmvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginView() throws Exception {
        mockMvc.perform(get("/auth/login"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType("text/html;charset=UTF-8"),
                        content().string(Matchers.containsString("Login"))
                );
    }

    @Test
    void SuccessLoginPost() throws Exception {
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("email", "admin@example.com")
                .param("password", "admin123")
        ).andExpectAll(
                status().is3xxRedirection(),
                redirectedUrl("/dashboard")
        );
    }

    @Test
    void ErrorLoginPost() throws Exception {
        mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .param("email", "")
        .param("password", "asdasd")
        ).andExpectAll(
                status().isOk(),
                model().attributeExists("loginForm"),
                model().attributeHasFieldErrors("loginForm", "email")
        );
    }
}
