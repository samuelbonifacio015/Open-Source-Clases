package com.acme.learning.center.platform.learning.domain.exceptions;

/**
 * Exception thrown when an error occurs during the enrollment request process.
 */
public class EnrollmentRequestException extends RuntimeException {
  /**
   * Constructor for EnrollmentRequestException.
   * @param exceptionMessage The message of the exception.
   */
  public EnrollmentRequestException(String exceptionMessage) {
    super("Error while creating the enrollment: %s".formatted(exceptionMessage));
  }
}
