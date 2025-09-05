package acme.oop.shared.domain.model.valueobjects;

import java.util.Objects;

/**
 * Represents a physical address with street, city, postal code, and country.
 * All fields are mandatory and cannot be null or blank.
 * @param street the street address, it must not be null or blank.
 * @param city the city name, it must not be null or blank.
 * @param postalCode the postal code, it must not be null or blank.
 * @param country the country name, it must not be null or blank.
 * @author Open Source Application Development Team
 */
public record Address(String street, String city, String postalCode, String country) {
    /**
     * Constructs an Address object with validation.
     * @param street the street address, it must not be null or blank.
     * @param city the city name, it must not be null or blank.
     * @param postalCode the postal code, it must not be null or blank.
     * @param country the country name, it must not be null or blank.
     * @throws IllegalArgumentException if any of the parameters is null or blank.
     */
    public Address {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be null or blank");
        }
        if (Objects.isNull(city) || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or blank");
        }
        if (Objects.isNull(postalCode)  || postalCode.isBlank()) {
            throw new IllegalArgumentException("Postal code cannot be null or blank");
        }
        if (Objects.isNull(country)  || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or blank");
        }
    }

    public String toString() {
        return street + ", " + city + ", " + postalCode + ", " + country;
        //return String.format("%s, %s, %s, %s", street, city, postalCode, country);
    }
}
