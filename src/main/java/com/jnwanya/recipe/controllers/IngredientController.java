package com.jnwanya.recipe.controllers;

import com.jnwanya.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;

/**
 * Created by Jnwanya on
 * Wed, 10 Jan, 2018.
 */
@Controller
public class IngredientController {

    private final RecipeService recipeService;

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
}
