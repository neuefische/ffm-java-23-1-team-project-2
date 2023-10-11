package de.neuefische.backend.controller;

import de.neuefische.backend.model.NewRecipe;
import de.neuefische.backend.model.Recipe;
import de.neuefische.backend.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    //@ResponseStatus
    @ResponseBody
    @PostMapping
    public Recipe postRecipe(@RequestBody NewRecipe newRecipe){
        System.out.println("Aufruf von Post");
        if (!newRecipe.title().isEmpty() && !newRecipe.description().isEmpty()){

        Recipe saveRecipe = new Recipe(UUID.randomUUID().toString(),newRecipe.title(),newRecipe.description());
            return recipeService.save(saveRecipe);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no data"); //"no data" nicht im postman, wieso?
    }
}
