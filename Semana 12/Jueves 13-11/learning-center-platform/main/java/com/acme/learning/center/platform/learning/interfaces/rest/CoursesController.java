package com.acme.learning.center.platform.learning.interfaces.rest;


import com.acme.learning.center.platform.learning.domain.model.commands.UpdateCourseCommand;
import com.acme.learning.center.platform.learning.domain.model.queries.GetAllCoursesQuery;
import com.acme.learning.center.platform.learning.domain.model.queries.GetCourseByIdQuery;
import com.acme.learning.center.platform.learning.domain.services.CourseCommandService;
import com.acme.learning.center.platform.learning.domain.services.CourseQueryService;
import com.acme.learning.center.platform.learning.interfaces.rest.resource.CourseResource;
import com.acme.learning.center.platform.learning.interfaces.rest.resource.CreateCourseResource;
import com.acme.learning.center.platform.learning.interfaces.rest.resource.UpdateCourseResource;
import com.acme.learning.center.platform.learning.interfaces.rest.transform.CourseResourceFromEntityAssembler;
import com.acme.learning.center.platform.learning.interfaces.rest.transform.CreateCourseCommandFromResourceAssembler;
import com.acme.learning.center.platform.learning.interfaces.rest.transform.UpdateCourseCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/courses", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Courses", description = "Courses API")
public class CoursesController {
  private final CourseCommandService courseCommandService;
  private final CourseQueryService courseQueryService;

  public CoursesController(CourseCommandService courseCommandService,
                           CourseQueryService courseQueryService) {
    this.courseCommandService = courseCommandService;
    this.courseQueryService = courseQueryService;
  }

  @PostMapping
  @Operation(summary = "Create a new course", description = "Create a new course with the provided details")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Course created successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid input data"),
      @ApiResponse(responseCode = "404", description = "Course could not be found")
  })
  public ResponseEntity<CourseResource> createCourse(@RequestBody CreateCourseResource resource) {
    var createCourseCommand = CreateCourseCommandFromResourceAssembler.toCommandFromResource(resource);
    var courseId = courseCommandService.handle(createCourseCommand);
    if (courseId == null || courseId == 0L) return ResponseEntity.badRequest().build();
    var course = courseQueryService.handle(new GetCourseByIdQuery(courseId));
    if (course.isEmpty()) return ResponseEntity.notFound().build();
    var courseResource = CourseResourceFromEntityAssembler.toResourceFromEntity(course.get());
    return new ResponseEntity<>(courseResource, HttpStatus.CREATED);
  }

  @GetMapping
  @Operation(summary = "Get all courses", description = "Get all courses")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Courses found"),
      @ApiResponse(responseCode = "404", description = "Course scould not be found")
  })
  public ResponseEntity<List<CourseResource>> getAllCourses() {
    var courses = courseQueryService.handle(new GetAllCoursesQuery());
    if (courses.isEmpty()) return ResponseEntity.notFound().build();
    var courseResources = courses.stream()
        .map(CourseResourceFromEntityAssembler::toResourceFromEntity)
        .toList();
    return ResponseEntity.ok(courseResources);
  }

  @GetMapping("/{courseId}")
  @Operation(summary = "Get all courses", description = "Get all courses")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Courses found"),
      @ApiResponse(responseCode = "404", description = "Courses not found")
  })
  public ResponseEntity<CourseResource> getCourseById(@PathVariable Long courseId) {
    var course = courseQueryService.handle(new GetCourseByIdQuery(courseId));
    if (course.isEmpty()) return ResponseEntity.notFound().build();
    var courseResources = CourseResourceFromEntityAssembler.toResourceFromEntity(course.get());
    return ResponseEntity.ok(courseResources);
  }


  @PutMapping("/{courseId}")
  @Operation(summary = "Update course", description = "Update course")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Courses updated"),
      @ApiResponse(responseCode = "404", description = "Courses not found")
  })
  public ResponseEntity<CourseResource> updateCourse(@PathVariable Long courseId,
                                                     @RequestBody UpdateCourseResource resource) {
    var updateCourseCommand = UpdateCourseCommandFromResourceAssembler.toCommandFromResource(courseId,resource);
    var updatedCourse = courseCommandService.handle(updateCourseCommand);
    if (updatedCourse.isEmpty()) return ResponseEntity.notFound().build();
    var updatedCourseResource = CourseResourceFromEntityAssembler.toResourceFromEntity(updatedCourse.get());
    return ResponseEntity.ok(updatedCourseResource);
  }










}
