package org.example.ch25.data;

import org.springframework.data.repository.CrudRepository;
import org.example.ch25.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
