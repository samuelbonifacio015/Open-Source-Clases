package com.acme.learning.center.platform.learning.domain.model.queries;

/**
 * Query to retrieve all enrollments for a specific course by its ID.
 */
public record GetEnrollmentsByCourseIdQuery(Long courseId) {

  public GetEnrollmentsByCourseIdQuery {
    if (courseId == null || courseId <= 0) {
      throw new IllegalArgumentException("Course ID must be a positive number.");
    }
  }
}
