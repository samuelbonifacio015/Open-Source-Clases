package com.citizen.platform.u202317269.citizen.domain.model.commands;

/**
 * Command to delete a citizen.
 * @author Samuel Bonifacio
 */
public record DeleteCitizenCommand(Long citizenId) {

  public DeleteCitizenCommand {
    if (citizenId == null || citizenId <= 0) {
      throw new IllegalArgumentException("Invalid citizen id, " +
          "must be a positive number.");
    }
  }
}
