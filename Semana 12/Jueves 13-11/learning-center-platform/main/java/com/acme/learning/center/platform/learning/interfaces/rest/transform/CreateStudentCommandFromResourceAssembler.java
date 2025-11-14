package com.acme.learning.center.platform.learning.interfaces.rest.transform;

import com.acme.learning.center.platform.learning.domain.model.commands.CreateStudentCommand;
import com.acme.learning.center.platform.learning.interfaces.rest.resource.CreateStudentResource;

public class CreateStudentCommandFromResourceAssembler {
  public static CreateStudentCommand toCommandFromResource(CreateStudentResource resource) {
    return new CreateStudentCommand(
        resource.firstName(),
        resource.lastName(),
        resource.email(),
        resource.street(),
        resource.number(),
        resource.city(),
        resource.postalCode(),
        resource.country()
    );
  }
}
