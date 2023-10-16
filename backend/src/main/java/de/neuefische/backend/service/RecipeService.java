package de.neuefische.backend.service;

import de.neuefische.backend.model.NewRecipe;
import de.neuefische.backend.model.Recipe;
import de.neuefische.backend.repository.RecipeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RecipeService {


    private final RecipeRepo recipeRepo;
    public RecipeService(RecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    public List <Recipe> getAll (){
        return recipeRepo.findAll();
    }

    public Recipe save(Recipe saveRecipe) {
        return recipeRepo.save(saveRecipe);
    }

    public Recipe getRecipeById(String id) {
        return recipeRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Recipe with id: " + id + " not found"));
    }

    public Recipe updateRecipe(String id, NewRecipe newRecipe) {
        Recipe updateTodo = new Recipe(id, newRecipe.title(), newRecipe.description());
        return recipeRepo.save(updateTodo);
    }

    public void delete(String id) { recipeRepo.deleteById(id);}

}
