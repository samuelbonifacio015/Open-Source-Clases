package com.acme.learning.center.platform.learning.application.internal.commandservices;

import com.acme.learning.center.platform.learning.domain.model.aggregates.Course;
import com.acme.learning.center.platform.learning.domain.model.commands.AddTutorialToCourseLearningPathCommand;
import com.acme.learning.center.platform.learning.domain.model.commands.CreateCourseCommand;
import com.acme.learning.center.platform.learning.domain.model.commands.DeleteCourseCommand;
import com.acme.learning.center.platform.learning.domain.model.commands.UpdateCourseCommand;
import com.acme.learning.center.platform.learning.domain.services.CourseCommandService;
import com.acme.learning.center.platform.learning.infrastructure.persistence.jpa.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseCommandServiceImpl implements CourseCommandService {
  private final CourseRepository courseRepository;

  public CourseCommandServiceImpl(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  @Override
  public Long handle(CreateCourseCommand command) {
    if (courseRepository.existsByTitle(command.title()))
      throw new IllegalArgumentException("Course with title " + command.title() + " already exists.");
    var course = new Course(command);
    try {
          courseRepository.save(course);
    } catch (Exception e) {
      throw new IllegalArgumentException("Error saving course: %s".formatted(e.getMessage()));
    }
    return course.getId();
  }

  @Override
  public Optional<Course> handle(UpdateCourseCommand command) {
    if (courseRepository.existsByTitleAndIdIsNot(command.title(), command.courseId()))
      throw new IllegalArgumentException("Course with title " + command.title() + " already exists.");
    var result = courseRepository.findById(command.courseId());
    if (result.isEmpty())
      throw new IllegalArgumentException("Course with id " + command.courseId() + " does not exist.");
    var courseToUpdate = result.get();
    try {
      var updateCourse = courseRepository.save(courseToUpdate.updateInformation(command.title(), command.description()));
      return Optional.of(updateCourse);
    } catch (Exception e) {
      throw new IllegalArgumentException("Error updating course: %s".formatted(e.getMessage()));
    }
  }

  @Override
  public void handle(DeleteCourseCommand command) {
    if (!courseRepository.existsById(command.courseId()))
      throw new IllegalArgumentException("Course with id " + command.courseId() + " does not exist.");
    try {
      courseRepository.deleteById(command.courseId());
    } catch (Exception e) {
      throw new IllegalArgumentException("Error deleting course: %s".formatted(e.getMessage()));
    }
  }

  @Override
  public void handle(AddTutorialToCourseLearningPathCommand command) {
      if (!courseRepository.existsById(command.courseId()))
        throw new IllegalArgumentException("Course with id " + command.courseId() + " does not exist.");
      try {
        courseRepository.findById(command.courseId()).map(
            course -> {
              course.addTutorialToLearningPath(command.tutorialId());
              courseRepository.save(course);
              return course;
            });
      }catch (Exception e) {
        throw new IllegalArgumentException("Error adding tutorial to course: %s".formatted(e.getMessage()));
      }
  }
}
