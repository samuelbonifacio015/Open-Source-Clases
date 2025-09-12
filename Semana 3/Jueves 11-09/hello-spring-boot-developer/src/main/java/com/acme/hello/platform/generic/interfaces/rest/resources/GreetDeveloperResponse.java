package com.acme.hello.platform.generic.interfaces.rest.resources;

import java.util.UUID;

/**
 * REST resource representing a Greet Developer response data.
 * @param id        the unique identifier of the developer
 * @param fullName  the full name of the developer
 * @param message   the greeting message
 * @author OpenSource Application Development Team
 * @version 1.0
 */
public record GreetDeveloperResponse(UUID id, String fullName,
                                     String message) {
    /**
     * Constructs a new GreetDeveloperResponse with the given id, fullName, and message.
     * @param message the greeting message, which may be null or empty
     */
    public GreetDeveloperResponse(String message) {
        this(null, null, message);
    }
}
