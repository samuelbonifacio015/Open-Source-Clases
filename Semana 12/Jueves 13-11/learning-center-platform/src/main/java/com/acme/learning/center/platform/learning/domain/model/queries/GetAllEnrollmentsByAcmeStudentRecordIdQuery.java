package com.acme.learning.center.platform.learning.domain.model.queries;

import com.acme.learning.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

/*
 * Query to retrieve all enrollments for a specific Acme student by their record ID.
 */
public record GetAllEnrollmentsByAcmeStudentRecordIdQuery(AcmeStudentRecordId studentRecordId) {
  public GetAllEnrollmentsByAcmeStudentRecordIdQuery {
    if (studentRecordId == null || studentRecordId.studentRecordId() == null
    || studentRecordId.studentRecordId().isBlank()) {
      throw new IllegalArgumentException("Student Record ID is required.");
    }
  }
}
