package de.neuefische.backend.repository;

import de.neuefische.backend.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepo extends MongoRepository<Recipe, String> {

}
