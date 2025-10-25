package com.acme.catchup.platform.news.interfaces.transform;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.interfaces.resources.FavoriteSourceResource;

public class FavoriteSourceResourceFromEntityAssembler {

    public static FavoriteSourceResource toResourceFromEntity(FavoriteSource entity) {
        return new FavoriteSourceResource(
            entity.getId(),
            entity.getNewsApiKey(),
            entity.getSourceId()
        );
    }
}
