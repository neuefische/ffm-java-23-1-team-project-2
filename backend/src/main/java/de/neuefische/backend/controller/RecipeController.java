package de.neuefische.backend.controller;

import de.neuefische.backend.model.NewRecipe;
import de.neuefische.backend.model.Recipe;
import de.neuefische.backend.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Recipe> getAllRecipes () {
        return recipeService.getAll();
    }

    @ResponseBody
    @PostMapping
    public ResponseEntity<Object> postRecipe(@RequestBody NewRecipe newRecipe) {
        if (!newRecipe.title().isEmpty() && !newRecipe.description().isEmpty()) {
            Recipe saveRecipe = new Recipe(UUID.randomUUID().toString(), newRecipe.title(), newRecipe.description());
            return ResponseEntity.ok(recipeService.save(saveRecipe));
        } else {
            return ResponseEntity.badRequest().body("Du Pfeife musst was eingeben!");
        }
    }
    @DeleteMapping("{id}")
    void delete(@PathVariable String id){
        recipeService.delete(id);
    }
}
