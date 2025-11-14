package com.acme.learning.center.platform.learning.domain.model.commands;

/**
 * Command to create a new course.
 */
public record CreateCourseCommand(String title,
                                  String description) {

  public CreateCourseCommand {
    if (title == null || title.isBlank()) {
      throw new IllegalArgumentException("Title cannot be null or blank");
    }
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("Description cannot be null or blank");
    }
  }
}
