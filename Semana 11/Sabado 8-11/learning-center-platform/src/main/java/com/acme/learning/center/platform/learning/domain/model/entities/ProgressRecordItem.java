package com.acme.learning.center.platform.learning.domain.model.entities;

import com.acme.learning.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.ProgressStatus;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.TutorialId;
import com.acme.learning.center.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
public class ProgressRecordItem extends AuditableModel {
  @ManyToOne
  @JoinColumn(name = "enrollment_id")
  private Enrollment enrollment;

  private TutorialId tutorialId;

  private ProgressStatus status;

  private Date statedAt;

  private Date completedAt;

  public ProgressRecordItem(Enrollment enrollment, TutorialId tutorialId) {
    this.enrollment = enrollment;
    this.tutorialId = tutorialId;
    this.status = ProgressStatus.NOT_STARTED;
  }

  public ProgressRecordItem() {
    // Default constructor for JPA
  }

  public void start() {
    this.status = ProgressStatus.STARTED;
    this.statedAt = new Date();
  }

  public void complete() {
    this.status = ProgressStatus.COMPLETED;
    this.completedAt = new Date();
  }

  public boolean isCompleted() { return this.status == ProgressStatus.COMPLETED;  }

  public boolean isStarted() { return this.status == ProgressStatus.STARTED;  }

  public boolean isNotStarted() { return  ProgressStatus.NOT_STARTED.equals(status);  }

  /**
   * Calculates the number of days elapsed since the tutorial was started.
   * @return the number of days elapsed
   */
  public long calculateDaysElapsed() {
    if (!isNotStarted()) { return 0; }
    var defaultTimezone = ZoneId.systemDefault();
    var fromDate = this.statedAt.toInstant().atZone(defaultTimezone);
    var toDate = Objects.isNull(completedAt)
        ? LocalDate.now().atStartOfDay(defaultTimezone).toInstant()
        : this.completedAt.toInstant();
    return Duration.between(fromDate, toDate).toDays();
  }

}
