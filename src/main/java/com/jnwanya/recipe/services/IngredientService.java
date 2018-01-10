package com.jnwanya.recipe.services;

import com.jnwanya.recipe.commands.IngredientCommand;

/**
 * Created by Jnwanya on
 * Wed, 10 Jan, 2018.
 */
public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long Id);

    IngredientCommand saveIngredientCommand(IngredientCommand command);
}
