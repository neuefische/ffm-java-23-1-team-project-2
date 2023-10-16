package de.neuefische.backend.service;
import de.neuefische.backend.model.NewRecipe;
import de.neuefische.backend.model.Recipe;
import de.neuefische.backend.repository.RecipeRepo;
import org.junit.jupiter.api.Test;

import java.util.*;

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
    void saveRecipe() {
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


    @Test
    void whenAddRecipeWithoutIdThenReturnRecipeWithId() {
        //GIVEN
        String id = "1";
        Recipe expected = new Recipe("1", "Test", "Test");
        //WHEN
        when(recipeRepo.findById(id)).thenReturn(Optional.of(expected));
        Recipe actual = recipeService.getRecipeById(id);
        //THEN
        verify(recipeRepo).findById(id);
        assertEquals(expected, actual);

    }
    @Test
    void updateRecipeShouldReturnUpdatedRecipe() {
        //GIVEN
        String id = "1";
        NewRecipe newRecipe = new NewRecipe("Test", "Test");
        Recipe expected = new Recipe("1", "Test", "Test");
        //WHEN
        when(recipeRepo.save(expected)).thenReturn(expected);
        Recipe actual = recipeService.updateRecipe(id, newRecipe);
        //THEN
        verify(recipeRepo).save(expected);
        assertEquals(expected, actual);
    }


    @Test
    void deleteRecipeById() {
        //GIVEN
        String id = "1648441351516816350";

        //WHEN
        recipeService.delete(id);
        //THEN
        verify(recipeRepo).deleteById(id);
    }

}
