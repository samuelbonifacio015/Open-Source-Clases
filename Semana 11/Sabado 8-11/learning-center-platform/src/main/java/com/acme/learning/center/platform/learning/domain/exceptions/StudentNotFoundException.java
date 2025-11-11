package com.acme.learning.center.platform.learning.domain.exceptions;

import com.acme.learning.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

/**
 * Exception thrown when a student with the specified ID is not found.
 */
public class StudentNotFoundException extends RuntimeException {
  /**
   * Constructor for StudentNotFoundException.
   * @param studentRecordId The ID of the course that was not found.
   */
  public StudentNotFoundException(AcmeStudentRecordId studentRecordId) {
    super("Student with ID " + studentRecordId + " not found.");
  }
}
