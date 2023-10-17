package de.neuefische.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.backend.model.Recipe;
import de.neuefische.backend.repository.RecipeRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RecipeControllerTest {

    @Autowired
    RecipeRepo recipeRepo;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    ClientRegistrationRepository clientRegistrationRepository;

    @Test
    @DirtiesContext
    void getAllRecipes() throws Exception {
        recipeRepo.save(new Recipe("1", "Nudeln", "5 minuten kochen"));

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

    @DirtiesContext
    @Test
    @WithMockUser
    void postRecipe() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                     {
                        "title": "Nudeln",
                        "description": "5 minuten kochen"
                     }
                    """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                     {

                        "title": "Nudeln",
                        "description": "5 minuten kochen"
                     }
                    """))
                .andExpect(jsonPath("$.id").isNotEmpty());


    }
    @DirtiesContext
    @Test
    @WithMockUser
    void testPostRecipeWithInvalidData() throws Exception {
        //GIVEN
        recipeRepo.save(new Recipe("1", "Nudeln", "5 minuten kochen"));
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "title":"",
                                "description": ""
                                }
                                """)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Du Pfeife musst was eingeben!"));
    }


    @DirtiesContext
    @Test
    @WithMockUser
    void expectSuccessfulPost() throws Exception {
        String actual = mockMvc.perform(
                        post("/api/recipes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {"title":"Test","description":"Test"}
                                        """)
                )
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                          "title": "Test",
                          "description": "Test"
                        }
                        """))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Recipe actualRecipe = objectMapper.readValue(actual, Recipe.class);
        assertThat(actualRecipe.id())
                .isNotBlank();
    }

    @DirtiesContext
    @Test
    @WithMockUser
    void deleteRecipeById() throws Exception {
        //GIVEN
        recipeRepo.save(new Recipe("1", "Test", "Test"));
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/recipes/1")
                        )
        //THEN
        .andExpect(status().isOk())
        .andExpect(content().string(""));

    }
}