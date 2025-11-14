package com.acme.learning.center.platform.learning.interfaces.rest.resource;

/**
 * CreateCourseResource
 * @param title Title of the course
 * @param description Description of the course
 */
public record CreateCourseResource(String title, String description) {

  public CreateCourseResource {
    if (title == null || title.isBlank()) {
      throw new IllegalArgumentException("Title cannot be null or blank");
    }
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("Description cannot be null or blank");
    }
  }
}
