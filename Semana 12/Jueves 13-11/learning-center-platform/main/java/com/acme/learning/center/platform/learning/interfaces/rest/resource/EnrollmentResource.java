package com.acme.learning.center.platform.learning.interfaces.rest.resource;

public record EnrollmentResource(
    Long enrollmentId,
    String studentRecordId,
    Long courseId,
    String status
) {
}
