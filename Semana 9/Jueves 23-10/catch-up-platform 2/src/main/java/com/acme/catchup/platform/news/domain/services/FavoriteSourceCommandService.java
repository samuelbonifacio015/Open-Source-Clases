package com.acme.catchup.platform.news.domain.services;

import com.acme.catchup.platform.news.domain.model.commands.CreateFavoriteSourceCommand;
import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;

import java.util.Optional;

/**
 * This interface represents the service to handle favorite source commands.
 */
public interface FavoriteSourceCommandService {
    /**
     * Handles the creation of a favorite source.
      * @param command The create favorite source command.
     * @return The created favorite source.
     *
     * @throws IllegalArgumentException If newsApiKey or sourceId in the command is null or blank.
     * @see CreateFavoriteSourceCommand
     */
    Optional<FavoriteSource> handle(CreateFavoriteSourceCommand command);
}
