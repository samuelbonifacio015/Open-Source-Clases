package com.acme.hello.platform.generic.interfaces.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * REST resource representing incoming Greet Developer request data.
 * @param firstName the first name of the developer
 * @param lastName the last name of the developer
 * @author Open Source Application Development Team
 * @version 1.0
 */
public record GreetDeveloperRequest(String firstName, String lastName) {
    /**
     * Constructor for GreetDeveloperRequest.
     * @param firstName Developer's first name
     * @param lastName Developer's last name
     */
    @JsonCreator
    public GreetDeveloperRequest(@JsonProperty("firstName") String firstName,
                                @JsonProperty("lastName") String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
