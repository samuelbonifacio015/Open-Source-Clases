package com.acme.learning.center.platform.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * ProfileId value object
 * @summary
 * This value object represents the unique identifier of a tutorial.
 * The profileId must be a positive number. It throws an IllegalArgumentException
 * if the provided profileId is null or not positive.
 */
@Embeddable
public record ProfileId(Long profileId) {

    public ProfileId {
        if (profileId == null || profileId <= 0) {
            throw new IllegalArgumentException("ProfileId must be a positive non-null value.");
        }
    }
}
