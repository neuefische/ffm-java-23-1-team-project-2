package de.neuefische.backend.controller;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RecipeControllerTest {


    @Autowired
    MockMvc mockMvc;

    private static MockWebServer mockWebServer;

    @BeforeAll
    static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @DynamicPropertySource
    static void setUrlDynamically(DynamicPropertyRegistry registry) {
        registry.add("http://localhost:8080/api/recipes", () -> mockWebServer.url("/").toString());
    }

    @Test
    @DirtiesContext
    void getAllRecipes() throws Exception {

        mockWebServer.enqueue(new MockResponse()
                .setBody(
                """
                       [
                     {
                        "id": "1",
                        "title": "Nudeln",
                        "description": "5 minuten kochen"
                     }
                    ]
                        """
                )
                .addHeader("Content-Type", "application/json")
        );

    mockMvc.perform(MockMvcRequestBuilders.get("/api/recipes"))
            .andExpect(status().isOk())
            .andExpect(content().json("""
                    [
                     {
                        "id": "1",
                        "title": "Nudeln",
                        "description": "5 minuten kochen"
                     }
                    ]
                    """));
    }

}