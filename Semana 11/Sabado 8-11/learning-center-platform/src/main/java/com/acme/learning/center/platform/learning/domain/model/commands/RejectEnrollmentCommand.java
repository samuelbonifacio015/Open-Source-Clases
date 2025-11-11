package com.acme.learning.center.platform.learning.domain.model.commands;

/**
 * Command to reject an enrollment.
 */
public record RejectEnrollmentCommand(Long enrollmentId) {
  public RejectEnrollmentCommand {
    if (enrollmentId == null || enrollmentId <= 0) {
      throw new IllegalArgumentException("Enrollment ID must be a positive number.");
    }
  }
}
