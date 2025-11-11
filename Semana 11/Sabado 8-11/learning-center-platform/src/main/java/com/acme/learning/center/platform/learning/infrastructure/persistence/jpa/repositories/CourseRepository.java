package com.acme.learning.center.platform.learning.infrastructure.persistence.jpa.repositories;

import com.acme.learning.center.platform.learning.domain.model.aggregates.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing Course entities in the database.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
  /**
   * Find a Course by its title.
   * @param title the title of the course
   * @return an Optional containing the Course if found, or empty if not found
   */
  Optional<Course> findByTitle(String title);

  /**
   * Check if a Course exists by its title.
   * @param title the title of the course
   * @return true if a Course with the given title exists, false otherwise
   */
  boolean existsByTitle(String title);

  /**
   * Check if a Course exists by its title excluding a specific id.
   * @param title the title of the course
   * @param id  the id to exclude from the check
   * @return true if a Course with the given title exists excluding the specified id, false otherwise
   */
  boolean existsByTitleAndIdIsNot(String title, Long id);
}
