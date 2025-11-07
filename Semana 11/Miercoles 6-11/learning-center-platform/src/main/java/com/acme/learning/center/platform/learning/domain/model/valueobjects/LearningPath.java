package com.acme.learning.center.platform.learning.domain.model.valueobjects;

import com.acme.learning.center.platform.learning.domain.model.aggregates.Course;
import com.acme.learning.center.platform.learning.domain.model.entities.LearningPathItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Learning Path Value Object
 * @summary
 * This value object represents a learning path consisting of multiple learning path items.
 */
@Embeddable
public class LearningPath {

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<LearningPathItem> learningPathItems;

    /**
     * Default constructor initializing an empty learning path.
     */
    public LearningPath() {
        this.learningPathItems = List.of();
    }

    /**
     * Get the first learning path item that matches the given predicate.
     * @param predicate The predicate to filter learning path items.
     * @return The first matching learning path item, or null if none found.
     */
    private LearningPathItem getFirstLearningPathItemWhere(Predicate<LearningPathItem> predicate) {
        return learningPathItems.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    /**
     * Get a learning path item by its ID.
     * @param itemId The ID of the learning path item.
     * @return The learning path item with the specified ID, or null if not found.
     */
    private LearningPathItem getLearningPathItemWithId(Long itemId) {
        return getFirstLearningPathItemWhere(item -> item.getId().equals(itemId));
    }

    /**
     * Get a learning path item by its tutorial ID.
     * @param tutorialId The tutorial ID of the learning path item.
     * @return The learning path item with the specified tutorial ID, or null if not found.
     */
    public LearningPathItem getLearningPathItemWithTutorialId(TutorialId tutorialId) {
        return this.getFirstLearningPathItemWhere(
                item -> item.getTutorialId().equals(tutorialId));
    }

    /**
     * Get the next tutorial ID in the learning path after the given current tutorial ID.
     * @param currentTutorialId The current tutorial ID.
     * @return The next tutorial ID in the learning path, or null if there is no next item.
     */
    public TutorialId getNextTutorialIdInLearningPath(TutorialId currentTutorialId) {
        LearningPathItem nextItem = getLearningPathItemWithTutorialId(currentTutorialId)
                .getNextItem();
        return !Objects.isNull(nextItem) ? nextItem.getTutorialId() : null;
    }

    /**
     * Check if the current tutorial ID is the last tutorial in the learning path.
     * @param currentTutorialId The current tutorial ID.
     * @return True if it is the last tutorial, false otherwise.
     */
    public boolean isLastTutorialInLearningPath(TutorialId currentTutorialId) {
        return Objects.isNull(getNextTutorialIdInLearningPath(currentTutorialId));
    }

    /**
     * Get the first tutorial ID in the learning path.
     * @return The first tutorial ID.
     */
    public TutorialId getFirstTutorialIdInLearningPath() {
        return learningPathItems.getFirst().getTutorialId();
    }

    /**
     * Get the last item in the learning path.
     * @return The last learning path item.
     */
    public LearningPathItem getLastItemInLearningPath() {
        return this.getFirstLearningPathItemWhere(
                item -> Objects.isNull(item.getNextItem()));
    }

    /**
     * Check if the learning path is empty.
     * @return True if the learning path has no items, false otherwise.
     */
    public boolean isEmpty() { return this.learningPathItems.isEmpty();   }

    /**
     * Add a new item to the learning path
     */
    public void addItem(Course course, TutorialId tutorialId, LearningPathItem nextItem) {
        LearningPathItem newItem = new LearningPathItem(course, tutorialId, nextItem);
        learningPathItems.add(newItem);
    }

    public void addItem(Course course, TutorialId tutorialId) {
        LearningPathItem newItem = new LearningPathItem(course, tutorialId, null);
        LearningPathItem originalLastItem = null;
        if (!this.isEmpty()) {
            originalLastItem = this.getLastItemInLearningPath();
        }
        learningPathItems.add(newItem);
        if (!Objects.isNull(originalLastItem)) originalLastItem.updateNextItem(newItem);
    }

    public void addItem(Course course, TutorialId tutorialId, TutorialId nextTutorialId) {
        LearningPathItem nextItem = this.getLearningPathItemWithTutorialId(nextTutorialId);
        addItem(course, tutorialId, nextItem);
    }



}
