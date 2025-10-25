package com.acme.catchup.platform.news.domain.services;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.queries.GetAllFavoriteSourcesByNewsApiKeyQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByIdQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByNewsApiKeyAndSourceIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * This interface represent the service to handle favorite source queries.
 */

public interface FavoriteSourceQueryService {

    /**
     * Handle the get all favorite sources by news api key query.
     * @param query - The get all favorite sources by news api key query.
     * @return - The list of favorite sources.
     * @throws IllegalArgumentException if newsApiKey in the query is null or blank.
     * @see GetAllFavoriteSourcesByNewsApiKeyQuery
     */
    List<FavoriteSource> handle(GetAllFavoriteSourcesByNewsApiKeyQuery query);

    /**
     * Handle the get favorite source by news api key and source id query.
     * @param query - The get favorite source by news api key and source id query.
     * @return - The favorite source.
     * @throws IllegalArgumentException if newsApiKey or sourceId in the query is null or blank.
     * @see GetFavoriteSourceByNewsApiKeyAndSourceIdQuery
     */
    Optional<FavoriteSource> handle(GetFavoriteSourceByNewsApiKeyAndSourceIdQuery query);

    /**
     * Handle the get favorite source by id query.
     * @param query - The get favorite source by id query.
     * @return - The favorite source.
     * @throws IllegalArgumentException if id in the query is null or blank.
     * @see GetFavoriteSourceByIdQuery
     */
    Optional<FavoriteSource> handle(GetFavoriteSourceByIdQuery query);
}
