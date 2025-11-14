package com.acme.learning.center.platform.learning.infrastructure.persistence.jpa.repositories;

import com.acme.learning.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.ProfileId;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Enrollment entities in the database.
 */
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
  /**
   * Find all Enrollments by AcmeStudentRecordId.
   * @param studentRecordId the AcmeStudentRecordId of the student
   * @return a list of Enrollments associated with the given AcmeStudentRecordId
   */
  List<Enrollment> findAllByAcmeStudentRecordId(AcmeStudentRecordId studentRecordId);

  /**
   * Find all Enrollments by CourseId.
   * @param courseId the id of the course
   * @return a list of Enrollments associated with the given CourseId
   */
  List<Enrollment> findAllByCourseId(Long courseId);

  /**
   * Find an Enrollment by AcmeStudentRecordId and CourseId.
   * @param studentRecordId the AcmeStudentRecordId of the student
   * @param courseId the id of the course
   * @return an Optional containing the Enrollment if found, or empty if not found
   */
  Optional<Enrollment> findByAcmeStudentRecordIdAndCourseId(AcmeStudentRecordId studentRecordId,
                                                            Long courseId);

}
