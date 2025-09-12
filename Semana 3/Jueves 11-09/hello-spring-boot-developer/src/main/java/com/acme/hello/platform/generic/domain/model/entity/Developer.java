package com.acme.hello.platform.generic.domain.model.entity;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * Represents a developer entity in the Generic bounded context.
 * @author OpenSource Application Development Team
 * @version 1.0.0
 */

@Getter
@Builder
public class Developer {
    private final UUID id = UUID.randomUUID();
    private final String firstName;
    private final String lastName;

    /**
     * Private constructor to enforce the use of the builder pattern.
     * @param firstName the first name of the developer.
     * @param lastName  the last name of the developer.
     */
    private Developer(String firstName, String lastName) {
        this.firstName = StringUtils.trimToEmpty(firstName);
        this.lastName = StringUtils.trimToEmpty(lastName);
    }

    /**
     * Returns the full name of the developer by concatenating firstName and lastName.
     * @return the full name of the developer.
     */
    public String getFullName() {
        return String.format("%s %s", firstName, lastName).trim();
    }

    /**
     * Checks if any of the name fields are blank (null, empty, or whitespace only).
     * @return true if either firstName or lastName is blank, false otherwise.
     */
    public boolean isAnyNameBlank() {
        return StringUtils.isAnyBlank(firstName, lastName);
    }

    /**
     * Checks if any of the name fields are empty (null or length of zero).
     * @return true if either firstName or lastName is empty, false otherwise.
     */
    public boolean isAnyNameEmpty() {
        return StringUtils.isAnyEmpty(firstName, lastName);
    }
}

