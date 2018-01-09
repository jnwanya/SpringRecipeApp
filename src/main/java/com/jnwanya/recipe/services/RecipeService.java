package com.jnwanya.recipe.services;

import com.jnwanya.recipe.domain.Recipe;

import java.util.Set;

/**
 * Created by Jnwanya on
 * Mon, 08 Jan, 2018.
 */
public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);
}
