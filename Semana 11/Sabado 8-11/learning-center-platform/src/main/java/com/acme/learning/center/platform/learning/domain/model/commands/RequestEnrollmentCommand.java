package com.acme.learning.center.platform.learning.domain.model.commands;

import com.acme.learning.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

public record RequestEnrollmentCommand(AcmeStudentRecordId studentRecordId,
                                       Long courseId) {
  public RequestEnrollmentCommand {
    if (studentRecordId == null ||
    studentRecordId.studentRecordId() == null ||
        studentRecordId.studentRecordId().isBlank())
        {
      throw new IllegalArgumentException("Student Record ID cannot be null or blank.");
    }
    if (courseId == null || courseId <= 0) {
      throw new IllegalArgumentException("Course ID must be a positive number.");
    }
  }
}
