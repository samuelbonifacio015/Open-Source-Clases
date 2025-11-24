package com.citizen.platform.u202317269.citizen.domain.model.valueobjects;

/**
 * Represents a Country value object in the domain model.
 * @author Samuel Bonifacio
 */
public record Country(CountryCode code) {

  public Country {
    if (code == null) {
      throw new IllegalArgumentException("Country code cannot be null");
    }
  }

  public String getCountryName() {
    return code.name();
  }

  public enum CountryCode {
    ARGENTINA,
    BOLIVIA,
    BRASIL,
    CHILE,
    COLOMBIA,
    ECUADOR,
    PARAGUAY,
    PERU,
    URUGUAY,
    VENEZUELA
  }
}
