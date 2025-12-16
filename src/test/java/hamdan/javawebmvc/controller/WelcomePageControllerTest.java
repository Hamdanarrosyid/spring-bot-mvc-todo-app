package hamdan.javawebmvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WelcomePageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void welcomePageTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpectAll(
                        status().isOk(),
                        content().string(Matchers.containsString("Welcome"))
                );
    }
}
