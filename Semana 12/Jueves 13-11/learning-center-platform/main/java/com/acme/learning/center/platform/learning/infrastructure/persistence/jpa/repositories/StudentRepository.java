package com.acme.learning.center.platform.learning.infrastructure.persistence.jpa.repositories;

import com.acme.learning.center.platform.learning.domain.model.aggregates.Student;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing Student entities in the database.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
  /**
   * Find a Student by their AcmeStudentRecordId.
   * @param studentRecordId the AcmeStudentRecordId of the student
   * @return an Optional containing the Student if found, or empty if not found
   */
  Optional<Student> findByAcmeStudentRecordId(AcmeStudentRecordId studentRecordId);

  /**
   * Find a Student by their ProfileId.
   * @param profileId the ProfileId of the student
   * @return an Optional containing the Student if found, or empty if not found
   */
  Optional<Student> findByProfileId(ProfileId profileId);
  /**
   * Check if a Student exists by their AcmeStudentRecordId.
   * @param studentRecordId the AcmeStudentRecordId of the student
   * @return true if a Student with the given AcmeStudentRecordId exists, false otherwise
   */
  boolean existsByAcmeStudentRecordId(AcmeStudentRecordId studentRecordId);
}
