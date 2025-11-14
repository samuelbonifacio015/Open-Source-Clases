package com.acme.learning.center.platform.learning.application.internal.queryservices;

import com.acme.learning.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.learning.center.platform.learning.domain.model.queries.*;
import com.acme.learning.center.platform.learning.domain.services.EnrollmentQueryService;
import com.acme.learning.center.platform.learning.infrastructure.persistence.jpa.repositories.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EnrollmentQueryServiceImpl implements EnrollmentQueryService {
  private final EnrollmentRepository enrollmentRepository;

  public EnrollmentQueryServiceImpl(EnrollmentRepository enrollmentRepository) {
    this.enrollmentRepository = enrollmentRepository;
  }

  @Override
  public List<Enrollment> handle(GetAllEnrollmentsQuery query) {
    return enrollmentRepository.findAll();
  }

  @Override
  public List<Enrollment> handle(GetAllEnrollmentsByAcmeStudentRecordIdQuery query) {
    return enrollmentRepository.findAllByAcmeStudentRecordId(query.studentRecordId());
  }

  @Override
  public Optional<Enrollment> handle(GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery query) {
    return enrollmentRepository.findByAcmeStudentRecordIdAndCourseId(query.studentRecordId(), query.courseId());
  }

  @Override
  public Optional<Enrollment> handle(GetEnrollmentByIdQuery query) {
    return enrollmentRepository.findById(query.enrollmentId());
  }

  @Override
  public List<Enrollment> handle(GetEnrollmentsByCourseIdQuery query) {
    return enrollmentRepository.findAllByCourseId(query.courseId());
  }
}
