package com.acme.learning.center.platform.learning.domain.model.queries;

public record GetEnrollmentByIdQuery(Long enrollmentId) {

  public GetEnrollmentByIdQuery {
    if (enrollmentId == null || enrollmentId <= 0) {
      throw new IllegalArgumentException("Enrollment ID must be a positive number.");
    }
  }
}
