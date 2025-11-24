package com.citizen.platform.u202317269.citizen.domain.model.queries;

/**
 * Query to get a citizen by id.
 * @author Samuel Bonifacio
 */
public record GetCitizenByIdQuery(Long citizenId) {

  public GetCitizenByIdQuery {
    if (citizenId == null || citizenId <= 0) {
      throw new IllegalArgumentException("Invalid citizen id, " +
          "must be a positive number.");
    }
  }

}
