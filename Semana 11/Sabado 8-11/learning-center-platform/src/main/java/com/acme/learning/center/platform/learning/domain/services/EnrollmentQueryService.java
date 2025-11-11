package com.acme.learning.center.platform.learning.domain.services;

import com.acme.learning.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.learning.center.platform.learning.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface EnrollmentQueryService {
  List<Enrollment> handle(GetAllEnrollmentsQuery query);
  List<Enrollment> handle(GetAllEnrollmentsByAcmeStudentRecordIdQuery query);
  Optional<Enrollment> handle(GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery  query);
  Optional<Enrollment> handle(GetEnrollmentByIdQuery query);
  List<Enrollment> handle(GetEnrollmentsByCourseIdQuery query);
}
