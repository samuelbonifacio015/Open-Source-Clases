package com.acme.catchup.platform.news.interfaces.resources;

public record CreateFavoriteSourceResource(String newsApiKey, String sourceId) {
    public CreateFavoriteSourceResource {
        if (newsApiKey == null || newsApiKey.isBlank()) {
            throw new IllegalArgumentException("newsApiKey must not be null or blank");
        }
        if (sourceId == null || sourceId.isBlank()) {
            throw new IllegalArgumentException("sourceId must not be null or blank");
        }
    }
}
