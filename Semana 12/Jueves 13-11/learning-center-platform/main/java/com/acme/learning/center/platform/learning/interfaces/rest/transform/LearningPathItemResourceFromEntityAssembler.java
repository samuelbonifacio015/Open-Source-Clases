package com.acme.learning.center.platform.learning.interfaces.rest.transform;

import com.acme.learning.center.platform.learning.domain.model.entities.LearningPathItem;
import com.acme.learning.center.platform.learning.interfaces.rest.resource.LearningPathItemResource;

/**
 * LearningPathItemResourceFromEntityAssembler
 */
public class LearningPathItemResourceFromEntityAssembler {
  public static LearningPathItemResource toResourceFromEntity(LearningPathItem entity) {
    return new LearningPathItemResource(entity.getId(),
        entity.getCourse().getId(), entity.getTutorialId().tutorialId());
  }
}
