package com.acme.learning.center.platform.learning.domain.model.commands;

/**
 * Command to update an existing course.
 */
public record UpdateCourseCommand(Long courseId, String title, String description) {

  public UpdateCourseCommand {
    if (courseId == null || courseId <= 0) {
      throw new IllegalArgumentException("Course ID must be a positive number.");
    }
    if (title == null || title.isBlank()) {
      throw new IllegalArgumentException("Title cannot be null or blank.");
    }
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("Description cannot be null or blank.");
    }
  }
}
