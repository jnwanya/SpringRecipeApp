package com.jnwanya.recipe.converters;

import com.jnwanya.recipe.commands.NotesCommand;
import com.jnwanya.recipe.domain.Notes;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Jnwanya on
 * Tue, 09 Jan, 2018.
 */
@Slf4j
@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand source) {
        if(source == null) {
            log.debug("NotesCommand is null");
            return null;
        }

        final Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        return notes;
    }
}
