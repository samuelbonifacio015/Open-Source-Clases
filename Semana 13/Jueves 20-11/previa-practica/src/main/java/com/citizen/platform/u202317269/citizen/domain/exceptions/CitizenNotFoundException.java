package com.citizen.platform.u202317269.citizen.domain.exceptions;

/**
 * Exception thrown when a citizen with the specified ID is not found.
 * @author Samuel Bonifacio
 */
public class CitizenNotFoundException extends RuntimeException {
  /**
   * Constructor for CitizenNotFoundException.
   * @param citizenId The ID of the citizen that was not found.
   */
  public CitizenNotFoundException(Long citizenId) {
    super("Citizen with ID: " + citizenId + " not found.");
  }
}
