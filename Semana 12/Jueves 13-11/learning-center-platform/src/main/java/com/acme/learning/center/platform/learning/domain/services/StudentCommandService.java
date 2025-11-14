package com.acme.learning.center.platform.learning.domain.services;

import com.acme.learning.center.platform.learning.domain.model.commands.CreateStudentCommand;
import com.acme.learning.center.platform.learning.domain.model.commands.UpdateStudentMetricsOnTutorialCompletedCommand;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

public interface StudentCommandService {

  AcmeStudentRecordId handle(CreateStudentCommand command);
  AcmeStudentRecordId handle(UpdateStudentMetricsOnTutorialCompletedCommand command);
}
