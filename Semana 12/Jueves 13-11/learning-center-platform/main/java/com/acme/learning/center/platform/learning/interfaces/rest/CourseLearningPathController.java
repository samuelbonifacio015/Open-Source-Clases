package com.acme.learning.center.platform.learning.interfaces.rest;

import com.acme.learning.center.platform.learning.domain.model.commands.AddTutorialToCourseLearningPathCommand;
import com.acme.learning.center.platform.learning.domain.model.queries.GetLearningPathItemByCourseIdAndTutorialIdQuery;
import com.acme.learning.center.platform.learning.domain.model.valueobjects.TutorialId;
import com.acme.learning.center.platform.learning.domain.services.CourseCommandService;
import com.acme.learning.center.platform.learning.domain.services.CourseQueryService;
import com.acme.learning.center.platform.learning.interfaces.rest.resource.LearningPathItemResource;
import com.acme.learning.center.platform.learning.interfaces.rest.transform.LearningPathItemResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/courses/{courseId}/learning-path-items", produces = APPLICATION_JSON_VALUE)
public class CourseLearningPathController {
  private final CourseCommandService courseCommandService;
  private final CourseQueryService courseQueryService;

  public CourseLearningPathController(CourseCommandService courseCommandService,
                                      CourseQueryService courseQueryService) {
    this.courseCommandService = courseCommandService;
    this.courseQueryService = courseQueryService;
  }

  @PostMapping("/{tutorialId}")
  @Operation(summary = "Add tutorial to the learning path of a course")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Tutorial added to the learning path"),
      @ApiResponse(responseCode = "404", description = "Courses or tutorial not found")
  })
  public ResponseEntity<LearningPathItemResource> addTutorialToCourseLeaningPath(
      @PathVariable Long courseId,
      @PathVariable Long tutorialId) {
    courseCommandService.handle(new AddTutorialToCourseLearningPathCommand(new TutorialId(tutorialId), courseId));
    var getLearningPathItemByCourseIdAndTutorialIdQuery =
        new GetLearningPathItemByCourseIdAndTutorialIdQuery(courseId, new TutorialId(tutorialId));
    var learningPathItem = courseQueryService.handle(getLearningPathItemByCourseIdAndTutorialIdQuery);
    if (learningPathItem.isEmpty()) { return ResponseEntity.notFound().build(); }
    var learningPathItemEntity = learningPathItem.get();
    var learningPathItemResource = LearningPathItemResourceFromEntityAssembler.toResourceFromEntity(learningPathItemEntity);
    return new ResponseEntity<>(learningPathItemResource, HttpStatus.CREATED);



  }
}
