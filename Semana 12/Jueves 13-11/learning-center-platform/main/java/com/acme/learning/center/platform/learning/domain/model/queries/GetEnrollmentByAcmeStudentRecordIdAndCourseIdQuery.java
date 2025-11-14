package com.acme.learning.center.platform.learning.domain.model.queries;

import com.acme.learning.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

/**
 * Query to retrieve a specific enrollment by Acme student record ID and course ID.
 */
public record GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery(
    AcmeStudentRecordId studentRecordId,
    Long courseId) {

  public GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery {
    if (studentRecordId == null || studentRecordId.studentRecordId() == null
        || studentRecordId.studentRecordId().isBlank()) {
      throw new IllegalArgumentException("Acme Student Record ID is required.");
    }
    if (courseId == null || courseId < 0)
      throw new IllegalArgumentException("Course ID is required and must positive.");
  }
}
