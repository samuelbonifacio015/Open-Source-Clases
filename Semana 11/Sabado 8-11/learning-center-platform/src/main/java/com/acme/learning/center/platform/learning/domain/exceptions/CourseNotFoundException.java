package com.acme.learning.center.platform.learning.domain.exceptions;

/**
 * Exception thrown when a course with the specified ID is not found.
 */
public class CourseNotFoundException extends RuntimeException {
  /**
   * Constructor for CourseNotFoundException.
   * @param courseId The ID of the course that was not found.
   */
  public CourseNotFoundException(Long courseId) {
    super("Course with ID " + courseId + " not found.");
  }
}
