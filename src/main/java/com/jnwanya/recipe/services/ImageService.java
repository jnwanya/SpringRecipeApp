package com.jnwanya.recipe.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Jnwanya on
 * Thu, 11 Jan, 2018.
 */
public interface ImageService {

    void saveImageFile(Long recipeId, MultipartFile file);
}
