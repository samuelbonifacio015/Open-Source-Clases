package com.acme.catchup.platform.news.infrastructure.persistence.jpa;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for FavoriteSource entity.
 * @summary
 * It extends JpaRepository to provide CRUD operations and custom queries for FavoriteSource.
 */
@Repository
public interface FavoriteSourceRepository extends JpaRepository<FavoriteSource, Long> {
    /**
     * Find all favorite sources by news API key.
     * @param newsApiKey The news API key.
     * @return List of FavoriteSource entities associated with the given news API key.
     */
    List<FavoriteSource> findAllByNewsApiKey(String newsApiKey);

    /**
     * Check if a favorite source exists by news API key and source ID.
     * @param newsApiKey - The news API key.
     * @param sourceId - The source ID.
     * @return True if a favorite source with the given news API key and source ID exists, false otherwise.
     */
    boolean existsByNewsApiKeyAndSourceId(String newsApiKey, String sourceId);

    /**
     * Find a favorite source by news API key and source ID.
     * @param newsApiKey The news API key.
     * @param sourceId The source ID.
     * @return An Optional containing the FavoriteSource if found, or empty if not found.
     */
    Optional<FavoriteSource> findByNewsApiKeyAndSourceId(String newsApiKey, String sourceId);
}
