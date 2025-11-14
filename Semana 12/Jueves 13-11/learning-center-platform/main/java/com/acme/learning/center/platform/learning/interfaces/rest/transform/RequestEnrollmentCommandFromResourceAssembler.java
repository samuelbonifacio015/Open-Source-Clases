package com.acme.learning.center.platform.learning.interfaces.rest.transform;

import com.acme.learning.center.platform.learning.domain.model.commands.RequestEnrollmentCommand;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.learning.center.platform.learning.interfaces.rest.resource.RequestEnrollmentResource;

public class RequestEnrollmentCommandFromResourceAssembler {

  public static RequestEnrollmentCommand toCommandFromResource(
      RequestEnrollmentResource resource) {
    return new RequestEnrollmentCommand(new AcmeStudentRecordId(resource.studentId()),
        resource.courseId());
  }
}
