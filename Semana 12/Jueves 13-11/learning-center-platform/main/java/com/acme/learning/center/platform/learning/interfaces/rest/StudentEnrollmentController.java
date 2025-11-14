package com.acme.learning.center.platform.learning.interfaces.rest;

import com.acme.learning.center.platform.learning.domain.model.queries.ExistsByAcmeStudentRecordIdQuery;
import com.acme.learning.center.platform.learning.domain.model.queries.GetAllEnrollmentsByAcmeStudentRecordIdQuery;
import com.acme.learning.center.platform.learning.domain.model.queries.GetStudentByAcmeStudentRecordIdQuery;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.learning.center.platform.learning.domain.services.EnrollmentQueryService;
import com.acme.learning.center.platform.learning.domain.services.StudentCommandService;
import com.acme.learning.center.platform.learning.domain.services.StudentQueryService;
import com.acme.learning.center.platform.learning.interfaces.rest.resource.CreateStudentResource;
import com.acme.learning.center.platform.learning.interfaces.rest.resource.EnrollmentResource;
import com.acme.learning.center.platform.learning.interfaces.rest.resource.StudentResource;
import com.acme.learning.center.platform.learning.interfaces.rest.transform.CreateStudentCommandFromResourceAssembler;
import com.acme.learning.center.platform.learning.interfaces.rest.transform.EnrollmentResourceFromEntityAssembler;
import com.acme.learning.center.platform.learning.interfaces.rest.transform.StudentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/students/{studentRecordId}/enrollments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Students Enrollment Controller", description = "Available Student Enrollment Endpoints")
public class StudentEnrollmentController {
  private final EnrollmentQueryService enrollmentQueryService;
  private final StudentQueryService studentQueryService;

  public StudentEnrollmentController(EnrollmentQueryService enrollmentQueryService,
                                     StudentQueryService studentQueryService) {
    this.enrollmentQueryService = enrollmentQueryService;
    this.studentQueryService = studentQueryService;
  }

  @GetMapping()
  @Operation(summary = "Get enrollments for a student")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Enrollments retrieved for a student"),
      @ApiResponse(responseCode = "404", description = "Student not found")
  })
  public ResponseEntity<List<EnrollmentResource>> getEnrollmentsForStudentWithStudentRecordId(@PathVariable String studentRecordId) {
    var existsStudent = new ExistsByAcmeStudentRecordIdQuery( new AcmeStudentRecordId(studentRecordId));
    if (!studentQueryService.handle(existsStudent)) { ResponseEntity.notFound().build(); }
    var enrollments = enrollmentQueryService.handle(
        new GetAllEnrollmentsByAcmeStudentRecordIdQuery(new AcmeStudentRecordId(studentRecordId)));
    var enrollmentResources = enrollments.stream()
        .map(EnrollmentResourceFromEntityAssembler::toResourceFromEntity).toList();
    return ResponseEntity.ok(enrollmentResources);

  }

}
