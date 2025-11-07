package com.acme.learning.center.platform.learning.domain.model.valueobjects;

import com.acme.learning.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.learning.center.platform.learning.domain.model.entities.ProgressRecordItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class ProgressRecord {

  @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
  private List<ProgressRecordItem> progressRecordItems;

  public  ProgressRecord() {
    progressRecordItems = new ArrayList<>();
  }

  /**
   * Initialize the progress record for a given enrollment and learning path
   */
  public void initializeProgressRecord(Enrollment enrollment,
                                       LearningPath learningPath) {
    if (learningPath.isEmpty()) return;
    TutorialId tutorialId = learningPath.getFirstTutorialIdInLearningPath();
    ProgressRecordItem progressRecordItem =
        new ProgressRecordItem(enrollment, tutorialId);
    progressRecordItems.add(progressRecordItem);
  }

  /**
   * Get ProgressRecordItem by TutorialId
   */
  private ProgressRecordItem getProgressRecordItemWithTutorialId(TutorialId tutorialId) {
    return progressRecordItems.stream()
        .filter(item -> item.getTutorialId().equals(tutorialId))
        .findFirst()
        .orElse(null);
  }

  /**
   * Check if there is any item in progress
   */
  private boolean hasAnItemInProgress() {
    return progressRecordItems.stream().anyMatch(ProgressRecordItem::isStarted);
  }

  /**
   * Start a tutorial by its TutorialId
   */
  public void startTutorial(TutorialId tutorialId) {
    if (hasAnItemInProgress()) { throw new IllegalStateException("A tutorial is already in progress."); }
    ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
    if (progressRecordItem != null) {
     if (progressRecordItem.isNotStarted()) { progressRecordItem.start(); }
     else throw new IllegalStateException("Tutorial is already in progress.");
    }
    else throw new IllegalArgumentException("Tutorial Id is not found.");
  }

  /**
   * Complete a tutorial by its TutorialId
   */
  public void completeTutorial(TutorialId tutorialId, LearningPath learningPath) {
    ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
    if (progressRecordItem != null) { progressRecordItem.complete();}
    else throw new IllegalArgumentException("Tutorial Id is not found.");
    if (learningPath.isLastTutorialInLearningPath(tutorialId)) return;
    TutorialId nextTutorialId = learningPath.getNextTutorialIdInLearningPath(tutorialId);
    if (nextTutorialId != null) {
      ProgressRecordItem nextProgressRecordItem =
          new ProgressRecordItem(progressRecordItem.getEnrollment(), nextTutorialId);
      progressRecordItems.add(nextProgressRecordItem);
    }
  }

  /**
   * Calculate the days elapsed for a given enrollment
   */
  public long calculateDaysElapsedForEnrollment(Enrollment enrollment) {
    return progressRecordItems.stream()
        .filter(progressRecordItem -> progressRecordItem.getEnrollment().equals(enrollment))
        .mapToLong(ProgressRecordItem::calculateDaysElapsed)
        .sum();
  }
}
