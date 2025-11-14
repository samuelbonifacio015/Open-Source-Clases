package com.acme.learning.center.platform.learning.interfaces.rest;

import com.acme.learning.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.learning.center.platform.learning.domain.model.commands.CancelEnrollmentCommand;
import com.acme.learning.center.platform.learning.domain.model.commands.ConfirmEnrollmentCommand;
import com.acme.learning.center.platform.learning.domain.model.commands.RejectEnrollmentCommand;
import com.acme.learning.center.platform.learning.domain.model.queries.GetAllEnrollmentsQuery;
import com.acme.learning.center.platform.learning.domain.model.queries.GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery;
import com.acme.learning.center.platform.learning.domain.services.EnrollmentCommandService;
import com.acme.learning.center.platform.learning.domain.services.EnrollmentQueryService;
import com.acme.learning.center.platform.learning.interfaces.rest.resource.EnrollmentResource;
import com.acme.learning.center.platform.learning.interfaces.rest.resource.RequestEnrollmentResource;
import com.acme.learning.center.platform.learning.interfaces.rest.transform.EnrollmentResourceFromEntityAssembler;
import com.acme.learning.center.platform.learning.interfaces.rest.transform.RequestEnrollmentCommandFromResourceAssembler;
import com.acme.learning.center.platform.shared.interfaces.rest.resources.MessageResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/enrollments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Enrollments", description = "Available Enrollment Endpoints")
public class EnrollmentsController {
  private final EnrollmentCommandService enrollmentCommandService;
  private final EnrollmentQueryService enrollmentQueryService;

  public EnrollmentsController(EnrollmentCommandService enrollmentCommandService,
                               EnrollmentQueryService enrollmentQueryService) {
    this.enrollmentCommandService = enrollmentCommandService;
    this.enrollmentQueryService = enrollmentQueryService;
  }
  @PostMapping
  @Operation(summary = "Request Enrollment")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Enrollment request successfully created"),
      @ApiResponse(responseCode = "400", description = "Invalid input data"),
      @ApiResponse(responseCode = "404", description = "Enrollment not found")
  })
  public ResponseEntity<EnrollmentResource> requestEnrollment(@RequestBody RequestEnrollmentResource resource) {
    var requestEnrollmentCommand =
        RequestEnrollmentCommandFromResourceAssembler.toCommandFromResource(resource);
    var enrollmentId = enrollmentCommandService.handle(requestEnrollmentCommand);
    if (enrollmentId == null || enrollmentId == 0L) {
      return ResponseEntity.badRequest().build();
    }
    var getEnrollmentByAcmeStudentRecordIdAndCourseIdQuery =
        new GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery(requestEnrollmentCommand.studentRecordId(),
            requestEnrollmentCommand.courseId());
    var enrollment = enrollmentQueryService.handle(getEnrollmentByAcmeStudentRecordIdAndCourseIdQuery);
    if (enrollment.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    var enrollmentEntity = enrollment.get();
    var enrollmentResource = EnrollmentResourceFromEntityAssembler.toResourceFromEntity(enrollmentEntity);
    return ResponseEntity.ok(enrollmentResource);
  }

  @PostMapping("/{enrollmentId}/confirmations")
  @Operation(summary = "Confirm Enrollment")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Confirm an enrollment"),
      @ApiResponse(responseCode = "400", description = "Invalid input data")
  })
  public ResponseEntity<MessageResource> confirmEnrollment(@PathVariable Long enrollmentId) {
    var confirmEnrollment = enrollmentCommandService.handle(new ConfirmEnrollmentCommand(enrollmentId));
    if (confirmEnrollment == null || confirmEnrollment.equals(0L)) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(new MessageResource("Enrollment confirmed successfully"));
  }

  @PostMapping("/{enrollmentId}/rejections")
  @Operation(summary = "Reject Enrollment")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Enrollment rejected successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid input data")
  })
  public ResponseEntity<MessageResource> rejectEnrollment(@PathVariable Long enrollmentId) {
    enrollmentCommandService.handle(new RejectEnrollmentCommand(enrollmentId));
    return ResponseEntity.ok(new MessageResource("Rejected Enrollment ID : " + enrollmentId));
  }

  @PostMapping("/{enrollmentId}/cancellations")
  @Operation(summary = "Cancel Enrollment")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Enrollment cancelled successfully"),
  })
  public ResponseEntity<MessageResource> cancelEnrollment(@PathVariable Long enrollmentId) {
    enrollmentCommandService.handle(new CancelEnrollmentCommand(enrollmentId));
    return ResponseEntity.ok(new MessageResource("Cancelled Enrollment ID : " + enrollmentId));
  }

  @GetMapping
  @Operation(summary = "Get all Enrollments")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Enrollments found"),
      @ApiResponse(responseCode = "404", description = "Enrollments not found")
  })
  public ResponseEntity<List<EnrollmentResource>> getAllEnrollments() {
    var enrollments = enrollmentQueryService.handle(new GetAllEnrollmentsQuery());
    if (enrollments.isEmpty()) { return ResponseEntity.notFound().build(); }
    var enrollmentResources = enrollments.stream()
        .map(EnrollmentResourceFromEntityAssembler::toResourceFromEntity)
        .toList();
    return ResponseEntity.ok(enrollmentResources);
  }
}
