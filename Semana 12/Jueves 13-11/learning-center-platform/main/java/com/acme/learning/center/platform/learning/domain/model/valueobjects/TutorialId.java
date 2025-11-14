package com.acme.learning.center.platform.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * TutorialId value object
 * @summary
 * This value object represents the unique identifier of a tutorial.
 * The tutorialId must be a positive number. It throws an IllegalArgumentException
 * if the provided tutorialId is null or not positive.
 */
@Embeddable
public record TutorialId(Long tutorialId) {

    public TutorialId {
        if (tutorialId == null || tutorialId < 0) {
            throw new IllegalArgumentException("Tutorial ID must be a positive number.");
        }
    }
}
