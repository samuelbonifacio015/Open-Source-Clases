package com.acme.learning.center.platform.learning.domain.model.commands;

import com.acme.learning.center.platform.learning.domain.model.valueobjects.TutorialId;

/**
 * Command to mark a tutorial as completed for a specific enrollment.
 */
public record CompleteTutorialForEnrollmentCommand(Long enrollmentId,
                                                  TutorialId tutorialId) {
  public CompleteTutorialForEnrollmentCommand {
    if (enrollmentId == null || enrollmentId <= 0) {
      throw new IllegalArgumentException("Enrollment ID must be a positive number.");
    }
    if (tutorialId == null || tutorialId.tutorialId() <= 0) {
      throw new IllegalArgumentException("Tutorial ID must be a positive number.");
    }
  }
}
