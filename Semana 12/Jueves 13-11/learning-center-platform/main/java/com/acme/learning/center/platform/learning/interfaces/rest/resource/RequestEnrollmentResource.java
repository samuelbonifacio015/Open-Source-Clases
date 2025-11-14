package com.acme.learning.center.platform.learning.interfaces.rest.resource;

public record RequestEnrollmentResource(String studentId, Long courseId) {

  public RequestEnrollmentResource {
    if (studentId == null || studentId.isBlank()) {
      throw new IllegalArgumentException("Student ID cannot be null or blank");
    }
    if (courseId == null || courseId <= 0) {
      throw new IllegalArgumentException("Course must be a positive number");
    }
  }
}
