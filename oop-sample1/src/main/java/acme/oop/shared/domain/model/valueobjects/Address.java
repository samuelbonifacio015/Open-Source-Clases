package acme.oop.shared.domain.model.valueobjects;

import java.util.Objects;

public record Address(String street, String city, String postalCode, String country) {
    public Address {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be null or blank");
        }
        if (Objects.isNull(city) || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or blank");
        }
        if (Objects.isNull(postalCode) || postalCode.isBlank()) {
            throw new IllegalArgumentException("PostalCode cannot be null or blank");
        }
        if (Objects.isNull(country) || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or blank");
        }
    }

    public String toString() {
        return street + ", " + city + ", " + postalCode + ", " + country;
        //return String.format("%s, %s, %s, %s", street, city, postalCode, country);
    }
}
