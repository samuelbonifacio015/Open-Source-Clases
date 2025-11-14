package com.acme.learning.center.platform.learning.interfaces.rest;

import com.acme.learning.center.platform.learning.domain.model.queries.GetStudentByAcmeStudentRecordIdQuery;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.learning.center.platform.learning.domain.services.StudentCommandService;
import com.acme.learning.center.platform.learning.domain.services.StudentQueryService;
import com.acme.learning.center.platform.learning.interfaces.rest.resource.CreateStudentResource;
import com.acme.learning.center.platform.learning.interfaces.rest.resource.StudentResource;
import com.acme.learning.center.platform.learning.interfaces.rest.transform.CreateStudentCommandFromResourceAssembler;
import com.acme.learning.center.platform.learning.interfaces.rest.transform.StudentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/students", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Students", description = "Available Student Endpoints")
public class StudentsController {
  private final StudentCommandService studentCommandService;
  private final StudentQueryService studentQueryService;

  public StudentsController(StudentCommandService studentCommandService,
                            StudentQueryService studentQueryService) {
    this.studentCommandService = studentCommandService;
    this.studentQueryService = studentQueryService;
  }

  @GetMapping("/{studentRecordId}")
  @Operation(summary = "Get student by Acme Student Record Id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Student found"),
      @ApiResponse(responseCode = "404", description = "Student not found")
  })
  public ResponseEntity<StudentResource> getStudentByAcmeStudentRecordId(@PathVariable String studentRecordId) {
    var student =
        studentQueryService.handle(new GetStudentByAcmeStudentRecordIdQuery(
            new AcmeStudentRecordId(studentRecordId)));
    if (student.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    var studentResource = StudentResourceFromEntityAssembler.toResourceFromEntity(student.get());
    return ResponseEntity.ok(studentResource);
  }

  @PostMapping
  @Operation(summary = "Create a new student")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Student created successfully"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "404", description = "Student not found")
  })
  public ResponseEntity<StudentResource> createStudent(CreateStudentResource resource) {
    var acmeStudentRecordId = studentCommandService.handle(
        CreateStudentCommandFromResourceAssembler.toCommandFromResource(resource));
    if (acmeStudentRecordId.studentRecordId().isEmpty()) {
      ;
      return ResponseEntity.badRequest().build();
    }
    var student = studentQueryService.handle(
        new GetStudentByAcmeStudentRecordIdQuery(acmeStudentRecordId));
    if (student.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    var studentResource = StudentResourceFromEntityAssembler.toResourceFromEntity(student.get());
    return new ResponseEntity<>(studentResource, HttpStatus.CREATED);
  }
}
