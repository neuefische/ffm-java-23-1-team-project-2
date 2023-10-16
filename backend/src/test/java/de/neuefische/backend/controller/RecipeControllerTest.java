package de.neuefische.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.backend.model.Recipe;
import de.neuefische.backend.repository.RecipeRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    @DirtiesContext
    void getAllRecipes() throws Exception {
        //GIVEN
        recipeRepo.save(new Recipe("1", "Nudeln", "5 minuten kochen"));
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/recipes"))
            //THEN
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
    void postRecipe() throws Exception{
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.post("/api/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                     {
                        "title": "Nudeln",
                        "description": "5 minuten kochen"
                     }
                    """))
        //THEN
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
                //THEN
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Du Pfeife musst was eingeben!"));
    }


    @DirtiesContext
    @Test
    void expectSuccessfulPost() throws Exception {
        //WHEN
        String actual = mockMvc.perform(
                        post("/api/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                        {
                                        "title":"Test",
                                        "description":"Test"}
                                 """)
                                        )
        //THEN
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
    @Test
    @DirtiesContext
    void findRecipeById() throws Exception {
        //GIVEN
        String body = mockMvc.perform(MockMvcRequestBuilders.post("/api/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "title": "Test",
                                "description": "Test"
                                }
                                """)
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Recipe response = objectMapper.readValue(body, Recipe.class);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/recipes/" + response.id()))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                         "title": "Test",
                         "description": "Test"
                        }
                        """))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }


    @Test
    @DirtiesContext
    void updateRecipeById() throws Exception {
        //GIVEN
        recipeRepo.save(new Recipe("1", "Test","Test"));
        //WHEN
        mockMvc.perform(put("/api/recipes/1")
                  .contentType(MediaType.APPLICATION_JSON)
                   .content("""
                            {
                            "title":"Test",
                            "description":"Test"}
                            """))
        //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                            "title": "Test",
                            "description": "Test"
                            }
                            """))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }


    @DirtiesContext
    @Test
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