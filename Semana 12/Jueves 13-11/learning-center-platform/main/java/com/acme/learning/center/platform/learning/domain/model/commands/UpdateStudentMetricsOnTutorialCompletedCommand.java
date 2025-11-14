package com.acme.learning.center.platform.learning.domain.model.commands;

import com.acme.learning.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

/**
 * Command to update student metrics when a tutorial is completed.
 */
public record UpdateStudentMetricsOnTutorialCompletedCommand(
    AcmeStudentRecordId studentRecordId) {
  public UpdateStudentMetricsOnTutorialCompletedCommand {
    if (studentRecordId == null || studentRecordId.studentRecordId() == null ||
    studentRecordId.studentRecordId().isBlank()) {
      throw new IllegalArgumentException("Student Record ID cannot be null or blank");
    }
  }
}
