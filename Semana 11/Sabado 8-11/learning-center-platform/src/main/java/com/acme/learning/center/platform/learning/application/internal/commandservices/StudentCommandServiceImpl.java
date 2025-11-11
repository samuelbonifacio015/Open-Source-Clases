package com.acme.learning.center.platform.learning.application.internal.commandservices;

import com.acme.learning.center.platform.learning.domain.exceptions.StudentNotFoundException;
import com.acme.learning.center.platform.learning.domain.model.commands.CreateStudentCommand;
import com.acme.learning.center.platform.learning.domain.model.commands.UpdateStudentMetricsOnTutorialCompletedCommand;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.learning.center.platform.learning.domain.services.StudentCommandService;
import com.acme.learning.center.platform.learning.infrastructure.persistence.jpa.repositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentCommandServiceImpl implements StudentCommandService {

  private final StudentRepository studentRepository;

  public StudentCommandServiceImpl(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }


  @Override
  public AcmeStudentRecordId handle(CreateStudentCommand command) {
    return null;
  }

  @Override
  public AcmeStudentRecordId handle(UpdateStudentMetricsOnTutorialCompletedCommand command) {
    studentRepository.findByAcmeStudentRecordId(command.studentRecordId())
        .map( student -> {
          //Update the student metrics
          student.updateMetricsOnTutorialCompleted();
          studentRepository.save(student);
          return  student.getAcmeStudentRecordId();
        }).orElseThrow(()-> new StudentNotFoundException(command.studentRecordId()));
    return null;
  }
}
