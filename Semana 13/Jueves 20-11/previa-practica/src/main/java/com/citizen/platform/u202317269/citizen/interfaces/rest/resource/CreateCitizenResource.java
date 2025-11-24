package com.citizen.platform.u202317269.citizen.interfaces.rest.resource;

import com.citizen.platform.u202317269.citizen.domain.model.valueobjects.Country;

import java.util.Date;

/**
 * CreateCitizenResource
 * @param name Name of the citizen
 * @param nickName Nickname of the citizen
 * @param birthDate Birth Date of the citizen
 * @param country Country of the citizen
 * @param profession Profession of the citizen
 * @author  Samuel Bonifacio
 */
public record CreateCitizenResource(String name, String nickName,
                                    Date birthDate, Country country, String profession) {
  public CreateCitizenResource {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be null or blank");
    }
    if (nickName == null || nickName.isBlank()) {
      throw new IllegalArgumentException("NickName cannot be null or blank");
    }
    if (country == null) {
      throw new IllegalArgumentException("Country cannot be null");
    }
    if (profession == null || profession.isBlank()) {
      throw new IllegalArgumentException("Profession cannot be null or blank");
    }
  }
}
