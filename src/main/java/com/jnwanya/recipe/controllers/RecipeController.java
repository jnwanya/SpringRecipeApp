package com.jnwanya.recipe.controllers;

import com.jnwanya.recipe.commands.RecipeCommand;
import com.jnwanya.recipe.exceptions.NotFoundException;
import com.jnwanya.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by Jnwanya on
 * Tue, 09 Jan, 2018.
 */
@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    private final String RECIPEFORM_URL = "recipe/recipeform";

    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(new Long(id)));

        return "recipe/show";
    }

    @RequestMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(new Long(id)));
        return RECIPEFORM_URL;
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id){
        log.debug("Delete recipe of ID: "+id);

        recipeService.deleteById(new Long(id));
        return "redirect:/";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> {
                log.debug(error.toString());
            });
            return RECIPEFORM_URL;
        }
        RecipeCommand recipeCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/"+ recipeCommand.getId() +"/show";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        log.error("handling not found error");
        log.error("Exception message", exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404Error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }


}
