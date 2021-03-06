package com.jnwanya.recipe.repositories;

import com.jnwanya.recipe.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Jnwanya on
 * Sun, 07 Jan, 2018.
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>{

    Optional<UnitOfMeasure> findByDescription(String description);
}
