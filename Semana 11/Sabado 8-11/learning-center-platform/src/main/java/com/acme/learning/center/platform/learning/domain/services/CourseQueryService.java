package com.acme.learning.center.platform.learning.domain.services;

import com.acme.learning.center.platform.learning.domain.model.aggregates.Course;
import com.acme.learning.center.platform.learning.domain.model.entities.LearningPathItem;
import com.acme.learning.center.platform.learning.domain.model.queries.GetAllCoursesQuery;
import com.acme.learning.center.platform.learning.domain.model.queries.GetCourseByIdQuery;
import com.acme.learning.center.platform.learning.domain.model.queries.GetLearningPathItemByCourseIdAndTutorialIdQuery;

import java.util.List;
import java.util.Optional;

public interface CourseQueryService {
  Optional<Course> handle(GetCourseByIdQuery query);
  List<Course> handle(GetAllCoursesQuery query);
  Optional<LearningPathItem> handle(GetLearningPathItemByCourseIdAndTutorialIdQuery query);
}
