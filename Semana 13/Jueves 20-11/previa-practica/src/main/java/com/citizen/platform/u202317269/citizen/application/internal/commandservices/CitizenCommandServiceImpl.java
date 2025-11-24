package com.citizen.platform.u202317269.citizen.application.internal.commandservices;

import com.citizen.platform.u202317269.citizen.domain.model.commands.CreateCitizenCommand;
import com.citizen.platform.u202317269.citizen.domain.model.commands.DeleteCitizenCommand;
import com.citizen.platform.u202317269.citizen.domain.services.CitizenCommandService;
import com.citizen.platform.u202317269.citizen.infrastructure.persistence.jpa.repositories.CitizenRepository;
import org.springframework.stereotype.Service;

/**
 * Implementation of the CitizenCommandService interface for handling citizen-related commands.
 * @author Samuel Bonifacio
 */
@Service
public class CitizenCommandServiceImpl implements CitizenCommandService {
  private final CitizenRepository citizenRepository;

  public CitizenCommandServiceImpl(CitizenRepository citizenRepository) {
    this.citizenRepository = citizenRepository;
  }

  @Override
  public Long handle(CreateCitizenCommand command) {
    if (citizenRepository.existsByNickName(command.nickName()))
      throw new IllegalArgumentException("Citizen with nickName " + command.nickName() + " already exists.");
    var citizen = new com.citizen.platform.u202317269.citizen.domain.model.aggregates.Citizen(command);
    try {
      citizenRepository.save(citizen);
    } catch (Exception e) {
      throw new IllegalArgumentException("Error saving citizen: %s".formatted(e.getMessage()));
    }
    return citizen.getId();
  }

  @Override
  public void handle(DeleteCitizenCommand command) {
    if (!citizenRepository.existsById(command.citizenId()))
      throw new IllegalArgumentException("Citizen with id " + command.citizenId() + " does not exist.");
    try {
      citizenRepository.deleteById(command.citizenId());
    } catch (Exception e) {
      throw new IllegalArgumentException("Error deleting citizen: %s".formatted(e.getMessage()));
    }
  }
}
