package com.acme.catchup.platform.news.application.commandservices;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.commands.CreateFavoriteSourceCommand;
import com.acme.catchup.platform.news.domain.services.FavoriteSourceCommandService;
import com.acme.catchup.platform.news.infrastructure.persistence.jpa.FavoriteSourceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoriteSourceCommandServiceImpl implements FavoriteSourceCommandService {

    private final FavoriteSourceRepository favoriteSourceRepository;

    public FavoriteSourceCommandServiceImpl(FavoriteSourceRepository favoriteSourceRepository) {
        this.favoriteSourceRepository = favoriteSourceRepository;
    }


    /**
     * Handles the creation of a favorite source.
     *
     * @param command The create favorite source command.
     * @return The created favorite source.
     * @throws IllegalArgumentException If newsApiKey or sourceId in the command is null or blank.
     * @see CreateFavoriteSourceCommand
     */
    @Override
    public Optional<FavoriteSource> handle(CreateFavoriteSourceCommand command) {
        if (favoriteSourceRepository.existsByNewsApiKeyAndSourceId(command.newsApiKey(), command.sourceId()))
            throw new IllegalArgumentException("Favorite source already exists for the given newsApiKey and sourceId");
        var favoriteSource = new FavoriteSource(command);
        var createdFavoriteSource = favoriteSourceRepository.save(favoriteSource);
        return Optional.of(createdFavoriteSource);
    }
}
