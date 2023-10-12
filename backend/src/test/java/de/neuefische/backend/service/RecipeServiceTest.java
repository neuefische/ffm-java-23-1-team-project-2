package de.neuefische.backend.service;
import de.neuefische.backend.model.Recipe;
import de.neuefische.backend.repository.RecipeRepo;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class RecipeServiceTest {

    RecipeRepo recipeRepo = mock(RecipeRepo.class);
    RecipeService recipeService = new RecipeService(recipeRepo);


    @Test
    void getAll() {
            //GIVEN
            Recipe recipe = new Recipe("1", "asddsa", "asdoh");

            List<Recipe> expected = new ArrayList<>(List.of(recipe));

            //WHEN
            when(recipeRepo.findAll()).thenReturn(expected);
            List<Recipe> actual = recipeService.getAll();

            //THEN
            verify(recipeRepo).findAll();
            assertEquals(expected, actual);

        }
    @Test
    void save() {
        //GIVEN
        Recipe savedRecipe = new Recipe("123", "Test", "Test");

        when(recipeRepo.save(savedRecipe)).thenReturn(savedRecipe);

        //WHEN
        Recipe actual = recipeService.save(savedRecipe);

        //THEN
        Recipe expected = new Recipe("123", "Test", "Test");
        verify(recipeRepo).save(savedRecipe);

        assertEquals(expected, actual);
    }
}
