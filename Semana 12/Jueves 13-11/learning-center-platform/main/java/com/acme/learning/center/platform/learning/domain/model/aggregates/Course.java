package com.acme.learning.center.platform.learning.domain.model.aggregates;

import com.acme.learning.center.platform.learning.domain.model.commands.CreateCourseCommand;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.LearningPath;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.TutorialId;
import com.acme.learning.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class Course extends AuditableAbstractAggregateRoot<Course> {
  private String title;
  private String description;

  @Embedded
  private final LearningPath learningPath;

  public Course() {
    this.learningPath = new LearningPath();
    this.title = "";
    this.description = "";
  }

  public Course(CreateCourseCommand command) {
    this.learningPath = new LearningPath();
    this.title = command.title();
    this.description = command.description();
  }

  public Course updateInformation(String title, String description) {
    this.title = title;
    this.description = description;
    return this;
  }

  /**
   * Adds a tutorial to the learning path.
   * @param tutorialId The ID of the tutorial to be added.
   */
  public void addTutorialToLearningPath(TutorialId tutorialId) {
    this.learningPath.addItem(this, tutorialId);
  }

  /**
   * Adds a tutorial to the learning path, specifying the next tutorial in the sequence.
   * @param tutorialId The ID of the tutorial to be added.
   * @param nextTutorialId The ID of the next tutorial in the learning path.
   */
  public void addTutorialToLearningPath(TutorialId tutorialId, TutorialId nextTutorialId) {
    this.learningPath.addItem(this, tutorialId, nextTutorialId);
  }
}
