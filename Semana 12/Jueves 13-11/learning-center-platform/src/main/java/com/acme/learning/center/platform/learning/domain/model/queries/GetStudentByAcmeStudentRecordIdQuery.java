package com.acme.learning.center.platform.learning.domain.model.queries;

import com.acme.learning.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

/**
 * Query to get a student by their Acme Student Record ID.
 */
public record GetStudentByAcmeStudentRecordIdQuery(AcmeStudentRecordId studentRecordId) {

  public GetStudentByAcmeStudentRecordIdQuery {
    if (studentRecordId == null || studentRecordId.studentRecordId() == null ||
        studentRecordId.studentRecordId().isBlank()) {
      throw new IllegalArgumentException("AcmeStudentRecordId cannot be null " +
          "or blank");
    }
  }
}
