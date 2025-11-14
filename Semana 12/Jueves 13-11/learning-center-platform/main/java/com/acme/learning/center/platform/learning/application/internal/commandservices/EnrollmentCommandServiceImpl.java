package com.acme.learning.center.platform.learning.application.internal.commandservices;

import com.acme.learning.center.platform.learning.domain.exceptions.CourseNotFoundException;
import com.acme.learning.center.platform.learning.domain.exceptions.EnrollmentNotFoundException;
import com.acme.learning.center.platform.learning.domain.exceptions.EnrollmentRequestException;
import com.acme.learning.center.platform.learning.domain.exceptions.StudentNotFoundException;
import com.acme.learning.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.learning.center.platform.learning.domain.model.commands.*;
import com.acme.learning.center.platform.learning.domain.services.EnrollmentCommandService;
import com.acme.learning.center.platform.learning.infrastructure.persistence.jpa.repositories.CourseRepository;
import com.acme.learning.center.platform.learning.infrastructure.persistence.jpa.repositories.EnrollmentRepository;
import com.acme.learning.center.platform.learning.infrastructure.persistence.jpa.repositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentCommandServiceImpl implements EnrollmentCommandService {

  private final CourseRepository courseRepository;
  private final StudentRepository studentRepository;
  private final EnrollmentRepository enrollmentRepository;

  public EnrollmentCommandServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, EnrollmentRepository enrollmentRepository) {
    this.courseRepository = courseRepository;
    this.studentRepository = studentRepository;
    this.enrollmentRepository = enrollmentRepository;
  }

  @Override
  public Long handle(RequestEnrollmentCommand command) {
    if (!studentRepository.existsByAcmeStudentRecordId(command.studentRecordId()))
      throw new StudentNotFoundException(command.studentRecordId());
    var course = courseRepository.findById(command.courseId())
        .orElseThrow(() -> new CourseNotFoundException(command.courseId()));
    try {
      var enrollment = new Enrollment(command.studentRecordId(), course);
      enrollmentRepository.save(enrollment);
      return enrollment.getId();
    } catch (Exception e) {
      throw new EnrollmentRequestException(e.getMessage());
    }
  }

  @Override
  public Long handle(ConfirmEnrollmentCommand command) {
    enrollmentRepository.findById(command.enrollmentId())
        .map( enrollment -> {
          enrollment.confirm();
          enrollmentRepository.save(enrollment);
          return enrollment.getId();
        }).orElseThrow(()-> new EnrollmentNotFoundException(command.enrollmentId()));
    return null;
  }

  @Override
  public Long handle(RejectEnrollmentCommand command) {
    enrollmentRepository.findById(command.enrollmentId())
        .map( enrollment -> {
          enrollment.reject();
          enrollmentRepository.save(enrollment);
          return enrollment.getId();
        }).orElseThrow(()-> new EnrollmentNotFoundException(command.enrollmentId()));
    return null;
  }

  @Override
  public Long handle(CancelEnrollmentCommand command) {
    enrollmentRepository.findById(command.enrollmentId())
        .map( enrollment -> {
          enrollment.cancel();
          enrollmentRepository.save(enrollment);
          return enrollment.getId();
        }).orElseThrow(()-> new EnrollmentNotFoundException(command.enrollmentId()));
    return null;
  }

  @Override
  public Long handle(CompleteTutorialForEnrollmentCommand command) {
    enrollmentRepository.findById(command.enrollmentId())
        .map( enrollment -> {
          enrollment.completeTutorial(command.tutorialId());
          enrollmentRepository.save(enrollment);
          return enrollment.getId();
        }).orElseThrow(()-> new EnrollmentNotFoundException(command.enrollmentId()));
    return null;
  }
}
