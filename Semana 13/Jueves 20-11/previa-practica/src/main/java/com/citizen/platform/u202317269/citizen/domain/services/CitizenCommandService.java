package com.citizen.platform.u202317269.citizen.domain.services;

import com.citizen.platform.u202317269.citizen.domain.model.aggregates.Citizen;
import com.citizen.platform.u202317269.citizen.domain.model.commands.CreateCitizenCommand;
import com.citizen.platform.u202317269.citizen.domain.model.commands.DeleteCitizenCommand;

import java.util.Optional;

/**
 * Service to handle citizen commands.
 * @author Samuel Bonifacio
 */

public interface CitizenCommandService {
  Long handle(CreateCitizenCommand command);
  void handle(DeleteCitizenCommand command);
}