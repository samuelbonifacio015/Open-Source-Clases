package com.acme.learning.center.platform.learning.interfaces.rest.transform;

import com.acme.learning.center.platform.learning.domain.model.commands.UpdateCourseCommand;
import com.acme.learning.center.platform.learning.interfaces.rest.resource.UpdateCourseResource;

/**
 * UpdateCourseCommandFromResourceAssembler
 */
public class UpdateCourseCommandFromResourceAssembler {
  public static UpdateCourseCommand toCommandFromResource(
      Long courseId, UpdateCourseResource resource) {
    return new UpdateCourseCommand(
        courseId, resource.title(),  resource.description()
    );
  }
}
