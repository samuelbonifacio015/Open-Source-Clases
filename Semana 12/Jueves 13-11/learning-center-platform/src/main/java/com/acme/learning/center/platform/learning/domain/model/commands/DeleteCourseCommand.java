package com.acme.learning.center.platform.learning.domain.model.commands;

/**
 * Command to delete an existing course.
 */
public record DeleteCourseCommand(Long courseId) {

  public DeleteCourseCommand {
    if (courseId == null || courseId <= 0) {
      throw new IllegalArgumentException("Course ID must be a positive number.");
    }
  }
}
