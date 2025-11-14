package com.acme.learning.center.platform.learning.domain.exceptions;

/**
 * Exception thrown when an enrollment with the specified ID is not found.
 */
public class EnrollmentNotFoundException extends RuntimeException {
  /**
   * Constructor for EnrollmentNotFoundException.
   * @param enrollmentId The ID of the course that was not found.
   */
  public EnrollmentNotFoundException(Long enrollmentId) {
    super("Enrollment with ID " + enrollmentId + " not found.");
  }
}
