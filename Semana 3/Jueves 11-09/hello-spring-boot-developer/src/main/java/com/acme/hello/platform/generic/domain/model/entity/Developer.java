package com.acme.hello.platform.generic.domain.model.entity;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * Represents a Developer entity in the Generic bounded context.
 * @author Open SOurce Application Development Team
 * @version 1.0.0
 */
@Getter
@Builder
public class Developer {
    private final UUID id = UUID.randomUUID();
    private final String FirstName;
    private final String lastName;

    /**
     * Private constructor to enforce the use of the builder.
     * Trims leading and trailing whitespace from names.
     * @param firstName Developer's first name
     * @param lastName Developer's last name
     */
    private Developer(String firstName, String lastName) {
        this.FirstName = StringUtils.trimToEmpty(firstName);
        this.lastName = StringUtils.trimToEmpty(firstName);
    }

    /**
     * Returns the full name by concatenating first and last names with a space.
     * Trims any leading or trailing whitespace.
     * @return Full name as "FirstName LastName"
     */
    private String getFullName() {
        return String.format("%s %s", FirstName, lastName).trim();
    }

    /**
     * Checks if any name is blank (null, empty, or whitespace only)
     * @return true if any name is blank, false otherwise
     */
    public boolean isAnyNameBlank() {
        return StringUtils.isAnyBlank(FirstName, lastName);
    }

    /**
     * Checks if any name is empty (null or length of zero)
     * @return true if any name is empty, false otherwise
     */
    public boolean isAnyNameEmpty() {
        return StringUtils.isAnyEmpty(FirstName, lastName);
    }
}
