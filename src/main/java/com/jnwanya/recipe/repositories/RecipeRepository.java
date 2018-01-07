package com.jnwanya.recipe.repositories;

import com.jnwanya.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Jnwanya on
 * Sun, 07 Jan, 2018.
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {



}
