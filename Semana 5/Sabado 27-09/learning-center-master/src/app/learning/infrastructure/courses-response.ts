export interface CoursesResponse{
  courses: CourseResource[];
}

export interface CourseResource{
  id: number;
  title: string;
  description: string;
}
