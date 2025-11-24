package com.citizen.platform.u202317269.citizen.domain.model.commands;

import com.citizen.platform.u202317269.citizen.domain.model.valueobjects.Country;
import java.util.Date;

/**
 * Command to create a new citizen.
 * @author Samuel Bonifacio
 */
public record CreateCitizenCommand(
    String name,
    String nickName,
    Date birthDate,
    Country country,
    String profession
) {
  public CreateCitizenCommand {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    if (nickName == null || nickName.isBlank()) {
      throw new IllegalArgumentException("NickName cannot be null or empty");
    }
    if (birthDate == null) {
      throw new IllegalArgumentException("Birth date cannot be null");
    }
    if (country == null) {
      throw new IllegalArgumentException("Country cannot be null");
    }
    if (profession == null || profession.isBlank()) {
      throw new IllegalArgumentException("Profession cannot be null or empty");
    }
  }
}
