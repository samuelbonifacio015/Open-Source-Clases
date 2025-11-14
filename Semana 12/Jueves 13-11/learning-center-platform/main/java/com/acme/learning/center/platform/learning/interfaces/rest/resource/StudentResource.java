package com.acme.learning.center.platform.learning.interfaces.rest.resource;

public record StudentResource(String acmeStudentRecordId, Long profileId,
                              Integer totalCompletedCourses, Integer totalCompletedTutorials) {
}
