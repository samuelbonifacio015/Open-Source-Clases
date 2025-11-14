package com.acme.learning.center.platform.learning.interfaces.rest.transform;

import com.acme.learning.center.platform.learning.domain.model.aggregates.Course;
import com.acme.learning.center.platform.learning.interfaces.rest.resource.CourseResource;

/**
 * CourseResourceFromEntityAssembler
 */
public class CourseResourceFromEntityAssembler {
  public static CourseResource toResourceFromEntity(Course entity) {
    return new CourseResource(
        entity.getId(),
        entity.getTitle(),
        entity.getDescription()
    );
  }
}
