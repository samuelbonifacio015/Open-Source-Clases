package com.acme.learning.center.platform.learning.domain.model.queries;

import com.acme.learning.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

/**
 * Query to check existence of a student record by its AcmeStudentRecordId.
 * @param studentRecordId
 */
public record ExistsByAcmeStudentRecordIdQuery(AcmeStudentRecordId studentRecordId) {
}
