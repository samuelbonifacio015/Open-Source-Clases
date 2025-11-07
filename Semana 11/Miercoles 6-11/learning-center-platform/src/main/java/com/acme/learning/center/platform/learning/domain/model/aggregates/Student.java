package com.acme.learning.center.platform.learning.domain.model.aggregates;

import com.acme.learning.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.ProfileId;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.StudentPerformanceMetricSet;
import com.acme.learning.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

/**
 * Student Aggregate Root entity
 * @summary
 * This class represents the Student aggregate root in the learning domain.
 * It extends the AuditableAbstractAggregateRoot to inherit auditing capabilities.
 * The Student entity contains embedded value objects for AcmeStudentRecordId,
 * ProfileId, and StudentPerformanceMetricSet.
 * @see AcmeStudentRecordId
 * @see ProfileId
 * @see StudentPerformanceMetricSet
 * @see AuditableAbstractAggregateRoot
 */
@Entity
public class Student extends AuditableAbstractAggregateRoot<Student> {

    @Getter
    @Embedded
    @Column(name = "acme_student_record_id")
    private AcmeStudentRecordId acmeStudentRecordId;

    @Embedded
    private ProfileId profileId;

    @Embedded
    private StudentPerformanceMetricSet performanceMetricSet;

    /**
     * Default constructor initializing embedded value objects
     */
    public Student() {
        super();
        this.acmeStudentRecordId = new AcmeStudentRecordId();
        this.performanceMetricSet = new StudentPerformanceMetricSet();
    }

    /**
     * Constructor with profileId
     * @param profileId Profile identifier
     */
    public Student(Long profileId) {
        this();
        this.profileId = new ProfileId(profileId);
    }

    /**
     * Constructor with ProfileId value object
     * @param profileId ProfileId value object
     */
    public Student(ProfileId profileId) {
        this();
        this.profileId = profileId;
    }

    /**
     * Updates performance metrics when a course is completed
     */
    public void updateMetricsOnCourseCompleted() {
        this.performanceMetricSet = this.performanceMetricSet.incrementCoursesCompleted();
    }

    /**
     * Updates performance metrics when a tutorial is completed
     */
    public void updateMetricsOnTutorialCompleted() {
        this.performanceMetricSet = this.performanceMetricSet.incrementTutorialsCompleted();
    }

    /**
     * Get student record ID
     */
    public String getStudentRecordId() {
        return this.acmeStudentRecordId.studentRecordId();
    }

    /**
     * Get profile ID
     */
    public Long getProfileId() {
        return this.profileId.profileId();
    }

    public int getTotalCompletedCourses() {
        return this.performanceMetricSet.totalCompletedCourses();
    }

    public int getTotalCompletedTutorials() {
        return this.performanceMetricSet.totalCompletedTutorials();
    }
}
