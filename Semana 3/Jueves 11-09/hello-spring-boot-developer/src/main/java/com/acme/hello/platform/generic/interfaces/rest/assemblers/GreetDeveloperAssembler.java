package com.acme.hello.platform.generic.interfaces.rest.assemblers;

import com.acme.hello.platform.generic.domain.model.entity.Developer;
import com.acme.hello.platform.generic.interfaces.rest.resources.GreetDeveloperResponse;
import org.apache.commons.lang3.ObjectUtils;

/**
 * Assembler to convert Developer entities to GreetDeveloperResponse resources.
 * @author OpenSource Application Development Team
 * @version 1.0
 */
public class GreetDeveloperAssembler {
    /**
     * Convert a Developer entity to a GreetDeveloperResponse resource.
     * @param developer the developer entity to convert
     * @return the corresponding GreetDeveloperResponse resource, or a default message if the developer is null or has empty name fields
     */
    public static GreetDeveloperResponse toResourceFromEntity(Developer developer) {
        if (isDeveloperNullOrEmptyNamed(developer)) {
            return new GreetDeveloperResponse(
                    "Welcome Anonymous Spring Boot Developer!");
        }
        return new GreetDeveloperResponse(
                developer.getId(),
                developer.getFullName(),
                "Congrats " + developer.getFullName()
                        + "! You are a Spring Boot Developer!");
    }

    /**
     * Check if the developer is null or has any empty name fields.
     * @param developer the developer to check
     * @return true if the developer is null or has any empty name fields, false otherwise
     */
    private static boolean isDeveloperNullOrEmptyNamed(Developer developer) {
        return ObjectUtils.isEmpty(developer) ||
               developer.isAnyNameBlank() ||
               developer.isAnyNameEmpty();
    }
}
