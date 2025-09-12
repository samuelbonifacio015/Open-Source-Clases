package com.acme.hello.platform.generic.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * REST resource representing incoming Greet Developer request data.
 *
 * @param firstName the first name of the developer
 * @param lastName  the last name of the developer
 * @author OpenSource Application Development Team
 * @version 1.0
 */
public record GreetDeveloperRequest(String firstName, String lastName) {

    /**
     * Constructs a new GreetDeveloperRequest instance with the given first and last names.
     * @param firstName the first name of the developer
     * @param lastName  the last name of the developer
     */
    @JsonCreator
    public GreetDeveloperRequest(@JsonProperty("firstName") String firstName,
                                 @JsonProperty("lastName") String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
