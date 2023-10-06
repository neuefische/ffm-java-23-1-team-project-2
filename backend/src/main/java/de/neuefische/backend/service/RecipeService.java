package de.neuefische.backend.service;

import de.neuefische.backend.model.Recipe;
import de.neuefische.backend.repository.RecipeRepo;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RecipeService {

    private final RecipeRepo recipeRepo;
    public RecipeService(RecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    public List <Recipe> getAll (){
        return recipeRepo.findAll();
    }


}
