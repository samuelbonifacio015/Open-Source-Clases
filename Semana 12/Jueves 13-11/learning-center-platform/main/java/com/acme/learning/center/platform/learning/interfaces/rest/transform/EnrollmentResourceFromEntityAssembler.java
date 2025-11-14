package com.acme.learning.center.platform.learning.interfaces.rest.transform;

import com.acme.learning.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.learning.center.platform.learning.interfaces.rest.resource.EnrollmentResource;

/**
 * EnrollmentResourceFromEntityAssembler
 */
public class EnrollmentResourceFromEntityAssembler {
  public static EnrollmentResource toResourceFromEntity(Enrollment entity) {
    return new EnrollmentResource(
        entity.getId(),
        entity.getAcmeStudentRecordId().studentRecordId(),
        entity.getCourse().getId(),
        entity.getStatus()
    );
  }
}
