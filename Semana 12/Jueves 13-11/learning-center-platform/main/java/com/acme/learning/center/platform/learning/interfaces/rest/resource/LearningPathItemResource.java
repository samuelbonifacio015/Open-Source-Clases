package com.acme.learning.center.platform.learning.interfaces.rest.resource;

/**
 *  Learning Path Item Resource
 */
public record LearningPathItemResource(Long learningPathItemId,
                                      Long courseId, Long tutorialId) {
}
