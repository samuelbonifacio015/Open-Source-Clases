package com.acme.learning.center.platform.learning.domain.services;

import com.acme.learning.center.platform.learning.domain.model.aggregates.Student;
import com.acme.learning.center.platform.learning.domain.model.queries.ExistsByAcmeStudentRecordIdQuery;
import com.acme.learning.center.platform.learning.domain.model.queries.GetStudentByAcmeStudentRecordIdQuery;
import com.acme.learning.center.platform.learning.domain.model.queries.GetStudentByProfileIdQuery;

import java.util.Optional;

public interface StudentQueryService {
  Optional<Student> handle(GetStudentByProfileIdQuery query);
  Optional<Student> handle(GetStudentByAcmeStudentRecordIdQuery query);
  boolean handle(ExistsByAcmeStudentRecordIdQuery query);
}
