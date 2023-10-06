package de.neuefische.backend.service;

import de.neuefische.backend.model.Recipe;
import de.neuefische.backend.repository.RecipeRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RecipeService {


    public List<Recipe> testList = new ArrayList<>(
            List.of(new Recipe("1", "Nudeln", "5 minuten kochen")
    ));

    private final RecipeRepo recipeRepo;
    public RecipeService(RecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    public List <Recipe> getAll (){
        //return recipeRepo.return();
        return testList;
    }


}
