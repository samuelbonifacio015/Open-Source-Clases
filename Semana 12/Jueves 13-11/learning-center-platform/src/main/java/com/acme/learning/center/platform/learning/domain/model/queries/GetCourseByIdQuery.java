package com.acme.learning.center.platform.learning.domain.model.queries;

/**
 * Query to get a course by id.
 */
public record GetCourseByIdQuery(Long courseId) {

  public GetCourseByIdQuery {
    if (courseId == null || courseId <= 0) {
      throw new IllegalArgumentException("Invalid course id, " +
          "must be a positive number.");
    }
  }
}
