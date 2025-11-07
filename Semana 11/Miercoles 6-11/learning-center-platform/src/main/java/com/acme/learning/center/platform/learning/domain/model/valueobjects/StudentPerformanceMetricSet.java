package com.acme.learning.center.platform.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Student Performance Metric Set Value Object
 * @summary
 * This value object encapsulates the performance metrics of a student,
 * It is embeddable within other entities.
 * @param totalCompletedCourses Total number of completed courses
 * @param totalCompletedTutorials Total number of completed tutorials
 */
@Embeddable
public record StudentPerformanceMetricSet(Integer totalCompletedCourses,
                                          Integer totalCompletedTutorials) {
    public StudentPerformanceMetricSet() {
        this(0,0);
    }

    public StudentPerformanceMetricSet {
        if (totalCompletedCourses == null || totalCompletedCourses < 0) {
            throw new IllegalArgumentException("totalCompletedCourses must be non-negative");
        }
        if (totalCompletedTutorials == null || totalCompletedTutorials < 0) {
            throw new IllegalArgumentException("totalCompletedTutorials must be non-negative");
        }
    }

    /**
     * Increments the total number of completed courses by 1.
     * @return A new StudentPerformanceMetricSet with updated completed courses count.
     */
    public StudentPerformanceMetricSet incrementCoursesCompleted() {
        return new StudentPerformanceMetricSet(
                this.totalCompletedCourses + 1, this.totalCompletedTutorials);
    }

    /**
     * Increments the total number of completed tutorials by 1.
     * @return A new StudentPerformanceMetricSet with updated completed tutorials count.
     */
    public StudentPerformanceMetricSet incrementTutorialsCompleted() {
        return new StudentPerformanceMetricSet(
                this.totalCompletedCourses, this.totalCompletedTutorials + 1);
    }
}
