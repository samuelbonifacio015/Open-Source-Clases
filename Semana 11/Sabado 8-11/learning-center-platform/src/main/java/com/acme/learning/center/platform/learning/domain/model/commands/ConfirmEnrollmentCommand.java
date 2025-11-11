package com.acme.learning.center.platform.learning.domain.model.commands;

/**
 * Command to confirm an enrollment.
 */
public record ConfirmEnrollmentCommand(Long enrollmentId) {
  public ConfirmEnrollmentCommand {
    if (enrollmentId == null || enrollmentId <= 0) {
      throw new IllegalArgumentException("Enrollment ID must be a positive number.");
    }
  }
}
