package com.acme.learning.center.platform.learning.application.internal.queryservices;

import com.acme.learning.center.platform.learning.domain.exceptions.CourseNotFoundException;
import com.acme.learning.center.platform.learning.domain.model.aggregates.Course;
import com.acme.learning.center.platform.learning.domain.model.entities.LearningPathItem;
import com.acme.learning.center.platform.learning.domain.model.queries.GetAllCoursesQuery;
import com.acme.learning.center.platform.learning.domain.model.queries.GetCourseByIdQuery;
import com.acme.learning.center.platform.learning.domain.model.queries.GetLearningPathItemByCourseIdAndTutorialIdQuery;
import com.acme.learning.center.platform.learning.domain.services.CourseQueryService;
import com.acme.learning.center.platform.learning.infrastructure.persistence.jpa.repositories.CourseRepository;

import java.util.List;
import java.util.Optional;

public class CourseQueryServiceImpl implements CourseQueryService {
  private final CourseRepository courseRepository;

  public CourseQueryServiceImpl(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  @Override
  public Optional<Course> handle(GetCourseByIdQuery query) {
    return courseRepository.findById(query.courseId());
  }

  @Override
  public List<Course> handle(GetAllCoursesQuery query) {
    return courseRepository.findAll();
  }

  @Override
  public Optional<LearningPathItem> handle(GetLearningPathItemByCourseIdAndTutorialIdQuery query) {
    if (!courseRepository.existsById(query.courseId())) {
     throw new CourseNotFoundException(query.courseId());
    }
    return courseRepository.findById(query.courseId())
        .map(course -> course.getLearningPath()
            .getLearningPathItemWithTutorialId(query.tutorialId()));
  }
}
