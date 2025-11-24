package com.citizen.platform.u202317269.citizen.interfaces.rest.transform;

import com.citizen.platform.u202317269.citizen.domain.model.aggregates.Citizen;
import com.citizen.platform.u202317269.citizen.interfaces.rest.resource.CitizenResource;

/**
 * CitizenResourceFromEntityAssembler
 * @author Samuel Bonifacio
 */
public class CitizenResourceFromEntityAssembler {
  public static CitizenResource toResourceFromEntity(Citizen entity) {
    return new CitizenResource(
        entity.getCitizenId(),
        entity.getName(),
        entity.getNickName(),
        entity.getBirthDate(),
        entity.getProfession()
    );
  }
}
