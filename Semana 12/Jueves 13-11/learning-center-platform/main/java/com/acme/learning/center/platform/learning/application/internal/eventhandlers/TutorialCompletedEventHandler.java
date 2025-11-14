package com.acme.learning.center.platform.learning.application.internal.eventhandlers;

import com.acme.learning.center.platform.learning.domain.model.commands.UpdateStudentMetricsOnTutorialCompletedCommand;
import com.acme.learning.center.platform.learning.domain.model.events.TutorialCompletedEvent;
import com.acme.learning.center.platform.learning.domain.model.queries.GetEnrollmentByIdQuery;
import com.acme.learning.center.platform.learning.domain.services.EnrollmentQueryService;
import com.acme.learning.center.platform.learning.domain.services.StudentCommandService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * Event handler for processing tutorial completion events.
 */
@Service
public class TutorialCompletedEventHandler {
  private final StudentCommandService studentCommandService;
  private final EnrollmentQueryService enrollmentQueryService;

  public TutorialCompletedEventHandler(StudentCommandService studentCommandService, EnrollmentQueryService enrollmentQueryService) {
    this.studentCommandService = studentCommandService;
    this.enrollmentQueryService = enrollmentQueryService;
  }

  @EventListener(TutorialCompletedEventHandler.class)
  public void on(TutorialCompletedEvent event) {
    var getEnrollmentByIdQuery = new GetEnrollmentByIdQuery(event.getEnrollmentId());
    var enrollment = enrollmentQueryService.handle(getEnrollmentByIdQuery);
    if (enrollment.isPresent()) {
      var studentEnrollment = enrollment.get();
      var updateStudentMetricsOnTutorialCompletedCommand =
          new UpdateStudentMetricsOnTutorialCompletedCommand(studentEnrollment.getAcmeStudentRecordId());
      studentCommandService.handle(updateStudentMetricsOnTutorialCompletedCommand);
    }
  }
}
