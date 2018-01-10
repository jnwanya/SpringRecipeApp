package com.jnwanya.recipe.services;

import com.jnwanya.recipe.commands.IngredientCommand;
import com.jnwanya.recipe.converters.IngredientCommandToIngredient;
import com.jnwanya.recipe.converters.IngredientToIngredientCommand;
import com.jnwanya.recipe.domain.Ingredient;
import com.jnwanya.recipe.domain.Recipe;
import com.jnwanya.recipe.domain.UnitOfMeasure;
import com.jnwanya.recipe.repositories.RecipeRepository;
import com.jnwanya.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Jnwanya on
 * Wed, 10 Jan, 2018.
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;


    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 RecipeRepository recipeRepository,
                                 UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent()){

            log.debug("recipe of id: "+recipeId+" not found");

        }
        Recipe recipe = recipeOptional.get();
        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            log.debug("ingredient of id: "+ingredientId+" not found");
        }

        return ingredientCommandOptional.get();
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        System.out.println(command.toString());
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){
            //todo toss error if not found
            log.error("recipe not found for "+command.getRecipeId());
            return new IngredientCommand();
        }else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientCommandOptional = recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst();

            if(ingredientCommandOptional.isPresent()){
                Ingredient ingredientFound = ingredientCommandOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
                        .findById(command.getUnitOfMeasure().getId())
                .orElseThrow(()-> new RuntimeException("UOM NOT FOUND")));
            }else{
                // for adding new ingredient
                recipe.addIngredient(ingredientCommandToIngredient.convert(command));
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
            .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst().get());
        }
    }


}
