package com.acme.learning.center.platform.learning.domain.model.aggregates;

import com.acme.learning.center.platform.learning.domain.model.events.TutorialCompletedEvent;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.EnrollmentStatus;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.ProgressRecord;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.TutorialId;
import com.acme.learning.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
public class Enrollment extends AuditableAbstractAggregateRoot<Enrollment> {

  @Getter
  @Embedded
  private AcmeStudentRecordId acmeStudentRecordId;


  @ManyToOne
  @JoinColumn(name = "course_id")
  @Getter
  private Course course;

  private ProgressRecord progressRecord;

  private EnrollmentStatus status;

  public Enrollment() {
    // Default constructor for JPA
  }

  public Enrollment(AcmeStudentRecordId acmeStudentRecordId, Course course) {
    this.acmeStudentRecordId = acmeStudentRecordId;
    this.course = course;
    this.progressRecord = new ProgressRecord();
    this.status = EnrollmentStatus.REQUESTED;
  }

  public void confirm() {
    this.status = EnrollmentStatus.CONFIRMED;
    this.progressRecord.initializeProgressRecord(this, course.getLearningPath());
  }

  public void reject() {
    this.status = EnrollmentStatus.REJECTED;
  }

  public void cancel() {
    this.status = EnrollmentStatus.CANCELLED;
  }

  public String getStatus() { return this.status.name().toLowerCase(); }

  public boolean isConfirmed() { return this.status == EnrollmentStatus.CONFIRMED;  }

  public boolean isRejected() { return this.status == EnrollmentStatus.REJECTED; }

  public boolean isCancelled() { return this.status == EnrollmentStatus.CANCELLED; }

  public void completeTutorial(TutorialId tutorialId) {
    this.progressRecord.completeTutorial(tutorialId, course.getLearningPath());
    this.registerEvent(new TutorialCompletedEvent(this, this.getId(), tutorialId));
  }

}
