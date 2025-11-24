package com.citizen.platform.u202317269.citizen.interfaces.rest.transform;

import com.citizen.platform.u202317269.citizen.domain.model.commands.CreateCitizenCommand;
import com.citizen.platform.u202317269.citizen.interfaces.rest.resource.CreateCitizenResource;

/**
 * CreateCitizenCommandFromResourceAssembler
 * @author Samuel Bonifacio
 */
public class CreateCitizenCommandFromResourceAssembler {
  public static CreateCitizenCommand toCommandFromResource(CreateCitizenResource resource) {
    return new CreateCitizenCommand(
        resource.name(),
        resource.nickName(),
        resource.birthDate(),
        resource.country(),
        resource.profession()
    );
  }
}
