package com.acme.catchup.platform.news.application.queryservices;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.queries.GetAllFavoriteSourcesByNewsApiKeyQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByIdQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByNewsApiKeyAndSourceIdQuery;
import com.acme.catchup.platform.news.domain.services.FavoriteSourceQueryService;
import com.acme.catchup.platform.news.infrastructure.persistence.jpa.FavoriteSourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FavoriteSourceQueryServiceImpl implements FavoriteSourceQueryService {

    private final FavoriteSourceRepository favoriteSourceRepository;

    public FavoriteSourceQueryServiceImpl(FavoriteSourceRepository favoriteSourceRepository) {
        this.favoriteSourceRepository = favoriteSourceRepository;
    }


    /**
     * Handle the get all favorite sources by news api key query.
     *
     * @param query - The get all favorite sources by news api key query.
     * @return - The list of favorite sources.
     * @throws IllegalArgumentException if newsApiKey in the query is null or blank.
     * @see GetAllFavoriteSourcesByNewsApiKeyQuery
     */
    @Override
    public List<FavoriteSource> handle(GetAllFavoriteSourcesByNewsApiKeyQuery query) {
        return favoriteSourceRepository.findAllByNewsApiKey(query.newsApiKey());
    }

    /**
     * Handle the get favorite source by news api key and source id query.
     *
     * @param query - The get favorite source by news api key and source id query.
     * @return - The favorite source.
     * @throws IllegalArgumentException if newsApiKey or sourceId in the query is null or blank.
     * @see GetFavoriteSourceByNewsApiKeyAndSourceIdQuery
     */
    @Override
    public Optional<FavoriteSource> handle(GetFavoriteSourceByNewsApiKeyAndSourceIdQuery query) {
        return favoriteSourceRepository.findByNewsApiKeyAndSourceId(query.newsApiKey(), query.sourceId());
    }

    /**
     * Handle the get favorite source by id query.
     *
     * @param query - The get favorite source by id query.
     * @return - The favorite source.
     * @throws IllegalArgumentException if id in the query is null or blank.
     * @see GetFavoriteSourceByIdQuery
     */
    @Override
    public Optional<FavoriteSource> handle(GetFavoriteSourceByIdQuery query) {
        return favoriteSourceRepository.findById(query.id());
    }
}
