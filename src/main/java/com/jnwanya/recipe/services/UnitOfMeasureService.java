package com.jnwanya.recipe.services;

import com.jnwanya.recipe.commands.UnitOfMeasureCommand;
import com.jnwanya.recipe.domain.UnitOfMeasure;

import java.util.Set;

/**
 * Created by Jnwanya on
 * Wed, 10 Jan, 2018.
 */
public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}
