package com.jnwanya.recipe.repositories;

import com.jnwanya.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Jnwanya on
 * Sun, 07 Jan, 2018.
 */
public interface CategoryRepository extends CrudRepository<Category, Long>{

    Optional<Category> findByDescription(String description);
}
