package com.jnwanya.recipe.controllers;

import com.jnwanya.recipe.domain.Category;
import com.jnwanya.recipe.domain.UnitOfMeasure;
import com.jnwanya.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by Jnwanya on
 * Sun, 07 Jan, 2018.
 */
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model){
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
