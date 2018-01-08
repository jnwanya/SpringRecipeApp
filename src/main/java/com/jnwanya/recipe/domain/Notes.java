package com.jnwanya.recipe.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Jnwanya on
 * Sun, 07 Jan, 2018.
 */
@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

}
