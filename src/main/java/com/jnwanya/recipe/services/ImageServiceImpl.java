package com.jnwanya.recipe.services;

import com.jnwanya.recipe.domain.Recipe;
import com.jnwanya.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Jnwanya on
 * Thu, 11 Jan, 2018.
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {
       log.debug("Recieved a file");

       try{
           Recipe recipe = recipeRepository.findById(recipeId).get();
           Byte[] byteObject = new Byte[file.getBytes().length];

           int i = 0;

           for(byte b: file.getBytes()){
               byteObject[i++] = b;
           }

           recipe.setImage(byteObject);

           recipeRepository.save(recipe);

       }catch (IOException ex){
           log.error("Error occured", ex);
           ex.printStackTrace();
       }
    }
}
