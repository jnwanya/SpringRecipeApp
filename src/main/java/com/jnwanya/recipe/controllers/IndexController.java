package com.jnwanya.recipe.controllers;

import com.jnwanya.recipe.domain.Category;
import com.jnwanya.recipe.domain.Recipe;
import com.jnwanya.recipe.domain.UnitOfMeasure;
import com.jnwanya.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Set;

/**
 * Created by Jnwanya on
 * Sun, 07 Jan, 2018.
 */
@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model){
        Set<Recipe> recipeSet = recipeService.getRecipes();
        log.debug("Size of recipes:", recipeSet.size());
        model.addAttribute("recipes", recipeSet);
        return "index";
    }
}
