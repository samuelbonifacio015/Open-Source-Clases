package com.acme.learning.center.platform.learning.interfaces.rest.transform;

import com.acme.learning.center.platform.learning.domain.model.aggregates.Student;
import com.acme.learning.center.platform.learning.interfaces.rest.resource.StudentResource;

/**
 * StudentResourceFromEntityAssembler
 */
public class StudentResourceFromEntityAssembler {
  public static StudentResource toResourceFromEntity(Student entity) {
    return new StudentResource(
        entity.getStudentRecordId(),
        entity.getProfileId(),
        entity.getTotalCompletedCourses(),
        entity.getTotalCompletedTutorials()
    );
  }
}
