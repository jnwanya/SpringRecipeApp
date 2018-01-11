package com.jnwanya.recipe.services;

import com.jnwanya.recipe.commands.RecipeCommand;
import com.jnwanya.recipe.converters.RecipeCommandToRecipe;
import com.jnwanya.recipe.converters.RecipeToRecipeCommand;
import com.jnwanya.recipe.domain.Recipe;
import com.jnwanya.recipe.exceptions.NotFoundException;
import com.jnwanya.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Jnwanya on
 * Mon, 08 Jan, 2018.
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("In get recipes method");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id){
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(!recipeOptional.isPresent()){
            throw new NotFoundException("Recipe Not Found");
        }
        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {

        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved reciped Id: "+savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(long l) {
        return recipeToRecipeCommand.convert(findById(l));
    }

    @Override
    public void deleteById(long idToDelete) {
       recipeRepository.deleteById(idToDelete);
    }
}
