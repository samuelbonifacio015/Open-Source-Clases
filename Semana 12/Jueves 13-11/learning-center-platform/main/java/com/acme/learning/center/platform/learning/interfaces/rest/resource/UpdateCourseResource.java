package com.acme.learning.center.platform.learning.interfaces.rest.resource;

/**
 * Update Course Resource
 */
public record UpdateCourseResource(String title, String description) {

  public UpdateCourseResource {
    if (title == null || title.isBlank()) {
      throw new IllegalArgumentException("Title cannot be null or blank");
    }
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("Description cannot be null or blank");
    }
  }
}
