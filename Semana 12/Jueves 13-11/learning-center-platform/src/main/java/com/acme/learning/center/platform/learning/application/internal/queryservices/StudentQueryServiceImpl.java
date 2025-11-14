package com.acme.learning.center.platform.learning.application.internal.queryservices;

import com.acme.learning.center.platform.learning.domain.model.aggregates.Student;
import com.acme.learning.center.platform.learning.domain.model.queries.ExistsByAcmeStudentRecordIdQuery;
import com.acme.learning.center.platform.learning.domain.model.queries.GetStudentByAcmeStudentRecordIdQuery;
import com.acme.learning.center.platform.learning.domain.model.queries.GetStudentByProfileIdQuery;
import com.acme.learning.center.platform.learning.domain.services.StudentQueryService;
import com.acme.learning.center.platform.learning.infrastructure.persistence.jpa.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the StudentQueryService interface for handling student-related queries.
 */
@Service
public class StudentQueryServiceImpl implements StudentQueryService {
  private final StudentRepository studentRepository;

  public StudentQueryServiceImpl(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public Optional<Student> handle(GetStudentByProfileIdQuery query) {
    return studentRepository.findByProfileId(query.profileId());
  }

  @Override
  public Optional<Student> handle(GetStudentByAcmeStudentRecordIdQuery query) {
    return studentRepository.findByAcmeStudentRecordId(query.studentRecordId());
  }

  @Override
  public boolean handle(ExistsByAcmeStudentRecordIdQuery query) {
    return studentRepository.existsByAcmeStudentRecordId(query.studentRecordId());
  }
}
