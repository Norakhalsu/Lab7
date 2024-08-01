package com.example.learning_management_system_lms.Service;

import com.example.learning_management_system_lms.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {
    ArrayList<Course> courses = new ArrayList<>();

    //get All Courses
    public ArrayList<Course> getCourses() {
        return courses;
    }


    // add new Course
    public void addCourse(Course course) {
        courses.add(course);
    }

    // update Course by course number
    public boolean updateCourse(String course_number ,Course course) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourse_number().equals(course_number )) {
                courses.set(i, course);
                return true;
            }
        }
        return false;
    }


    // delete Course by course name
    public boolean deleteCourse(String course_name) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourse_name().equalsIgnoreCase(course_name)) {
                courses.remove(i);
                return true;
            }
        }
        return false;
    }


    // take numbers of student  and get courses
    public ArrayList<Course> getCoursesByStudentNumbers(int student_number) {
        ArrayList<Course> courseAll = new ArrayList<>();
        for(Course course : courses) {
            if (course.getNumber_of_student()==student_number) {
                courseAll.add(course);
            }
        }
        return courseAll;
    }

    // Take course code and get only one course
    public Course getCourseByCode(String course_Code) {
            for (Course course : courses) {
                if (course.getCourse_code().equalsIgnoreCase(course_Code)) {
                    return course;
                }
            }
            return null;
         }

         // take course name and update day of course
        public boolean updatetDayByCourseName(String course_name , String day) {
        for (Course course : courses) {
            if (course.getCourse_name().equalsIgnoreCase(course_name)) {
                course.setCourse_day(day);
                return true;
             }
          }
        return false;
        }


        // take day and get all courses in this day
       public ArrayList<Course> getCoursesByDay(String course_day) {
        ArrayList<Course> coursesByDay = new ArrayList<>();
        for(Course course : courses) {
            if (course.getCourse_day().equalsIgnoreCase(course_day)) {
                coursesByDay.add(course);
            }
         }
          return coursesByDay;
       }


       // take course code and get hours of course
      public int getHoursByCourseCode(String course_code) {

        for (Course course : courses) {
            if (course.getCourse_code().equalsIgnoreCase(course_code)) {
                return course.getCourse_hours();
            }
        }
        return 0;
      }


}
