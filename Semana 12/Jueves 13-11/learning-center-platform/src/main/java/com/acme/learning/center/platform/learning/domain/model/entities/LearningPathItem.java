package com.acme.learning.center.platform.learning.domain.model.entities;

import com.acme.learning.center.platform.learning.domain.model.aggregates.Course;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.TutorialId;
import com.acme.learning.center.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * Learning Path Item Entity
 * @summary
 * This entity represents an item in a learning path, linking a course to a tutorial
 * and optionally to the next item in the path.
 */
@Getter
@Entity
public class LearningPathItem extends AuditableModel {
    @ManyToOne
    @JoinColumn(name = "course_id")
    @NotNull
    private Course course;

    @NotNull
    @Embedded
    @Column(name = "tutorial_id")
    private TutorialId tutorialId;

    @ManyToOne
    @JoinColumn(name = "next_item_id")
    private LearningPathItem nextItem;

    public LearningPathItem(Course course, TutorialId tutorialId, LearningPathItem nextItem) {
        this.course = course;
        this.tutorialId = tutorialId;
        this.nextItem = nextItem;
    }

    public LearningPathItem() {
        this.tutorialId = new TutorialId(0L);
        this.nextItem = null;
    }

    /**
     * Updates the next item in the learning path.
     * @param nextItem The new next item to be set.
     */
    public void updateNextItem(LearningPathItem nextItem) {
        this.nextItem = nextItem;
    }
}
