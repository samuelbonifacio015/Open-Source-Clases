package com.acme.learning.center.platform.learning.domain.model.queries;

import com.acme.learning.center.platform.learning.domain.model.valueobjects.TutorialId;

/**
 * Query to get a learning path item by course id and tutorial id.
 */
public record GetLearningPathItemByCourseIdAndTutorialIdQuery(
    Long courseId,
    TutorialId tutorialId
) {

  public GetLearningPathItemByCourseIdAndTutorialIdQuery {
    if (courseId == null || courseId <= 0) {
      throw new IllegalArgumentException("Invalid course id, " +
          "must be a positive number.");
    }
    if (tutorialId == null ||
        tutorialId.tutorialId() == null ||
        tutorialId.tutorialId() <= 0) {
      throw new IllegalArgumentException("Tutorial id cannot be null" +
          " and must be a positive number.");
    }
  }
}
