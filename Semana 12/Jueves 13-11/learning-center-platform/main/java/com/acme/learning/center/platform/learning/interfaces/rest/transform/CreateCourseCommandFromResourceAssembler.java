package com.acme.learning.center.platform.learning.interfaces.rest.transform;

import com.acme.learning.center.platform.learning.domain.model.commands.CreateCourseCommand;
import com.acme.learning.center.platform.learning.interfaces.rest.resource.CreateCourseResource;

/**
 * CreateCourseCommandFromResourceAssembler
 */
public class CreateCourseCommandFromResourceAssembler {
  public static CreateCourseCommand toCommandFromResource(CreateCourseResource resource) {
    return new CreateCourseCommand(
        resource.title(),
        resource.description()
    );
  }
}
