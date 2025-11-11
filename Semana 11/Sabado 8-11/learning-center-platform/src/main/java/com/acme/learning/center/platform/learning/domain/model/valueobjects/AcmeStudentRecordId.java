package com.acme.learning.center.platform.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

/**
 * Value object representing a unique identifier for a student's
 * record in the Acme Learning Center platform.
 * @param studentRecordId The unique identifier for the student record.
 */
@Embeddable
public record AcmeStudentRecordId(String studentRecordId) {
    public AcmeStudentRecordId {
        if (studentRecordId == null || studentRecordId.isBlank()) {
            throw new IllegalArgumentException("Student Record ID cannot be null or blank");
        }
    }

    /**
     * Default constructor that generates a random UUID as the student record ID.
     * @summary
     * This constructor is used to create a new instance of the AcmeStudentRecordId value object.
     * It generates a new random UUID string to be used as the student record ID.
     */
    public AcmeStudentRecordId() {
        this(UUID.randomUUID().toString());
    }
}
