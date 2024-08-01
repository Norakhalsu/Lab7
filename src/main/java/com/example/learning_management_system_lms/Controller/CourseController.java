package com.example.learning_management_system_lms.Controller;


import com.example.learning_management_system_lms.Model.Course;
import com.example.learning_management_system_lms.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;


    @GetMapping("/get") // get All Courses
    public ResponseEntity getAllCourses() {
        ArrayList<Course> arrayList=courseService.getCourses();
        return ResponseEntity.status(200).body(arrayList);
    }

    @PostMapping("/add")
    public ResponseEntity addCourse(@Valid @RequestBody Course course , Errors errors) {
        if (errors.hasErrors()) {
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(message);
        }
        courseService.addCourse(course);
        return ResponseEntity.status(201).body("Course added successfully");
    }


    // update course by Course Number
    @PutMapping("/update/{course_number}")
    public ResponseEntity updateCourse( @PathVariable String course_number, @Valid @RequestBody Course course , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
         if (courseService.updateCourse(course_number,course)){
             return ResponseEntity.status(201).body("Course updated successfully");
         }
         return ResponseEntity.status(404).body("Sorry Course not found");
    }


    // delete course by Course Name
    @DeleteMapping("/delete/{course_name}")
    public ResponseEntity deleteCourse( @PathVariable String course_name ) {
          // String deletbyname= courseService.deleteCourse(course_name);

        if ( courseService.deleteCourse(course_name) ){
            return ResponseEntity.status(201).body("Course deleted successfully");
        }
        return ResponseEntity.status(404).body(" Sorry Course not found");
    }


   @GetMapping("/student-number/{student_number}") // take student numbers and get courses
    public ResponseEntity getCourseByStudentNumber(@PathVariable int student_number ) {

        ArrayList<Course> studentCourse=courseService.getCoursesByStudentNumbers(student_number);

        if (studentCourse.isEmpty()){
            return ResponseEntity.status(404).body("Sorry Course not found");
        }
        return ResponseEntity.status(200).body(studentCourse);
     }


     @GetMapping("/course-code/{course_code}") // Take course code and get only one course
     public ResponseEntity getCourseByCode(@PathVariable String course_code) {
        if (courseService.getCourseByCode(course_code) != null){
            return ResponseEntity.status(200).body(courseService.getCourseByCode(course_code));
        }
        return ResponseEntity.status(404).body("Sorry Course Not Found");
     }


      @PutMapping("/update-day/{course_name}/{day}")// // take course name and update day of course
     public  ResponseEntity updateCourseDay( @PathVariable String course_name, @PathVariable String day ) {
    boolean updatrDay=courseService.updatetDayByCourseName(course_name , day);
        if(updatrDay){
            return  ResponseEntity.status(200).body("Update Day Successfully");
        }
        return ResponseEntity.status(404).body("Sorry Course not found");
      }

      @GetMapping("/all-courses/{day}")  // take day and get all courses in this day
      public ResponseEntity getAllCorses(@PathVariable String day) {
          ArrayList<Course> allCourse=courseService.getCoursesByDay(day);
          if (allCourse.isEmpty()){
              return ResponseEntity.status(404).body("Sorry Course not found");
          }
          return ResponseEntity.status(200).body(allCourse);
      }


      @GetMapping("/get-hours/{course_code}")//take course code and get hours of course
      public ResponseEntity getHoursByCourseCode(@PathVariable String course_code ) {

        if(courseService.getHoursByCourseCode(course_code)==0){
            return ResponseEntity.status(404).body("Sorry Course Not Found");
        }
        return ResponseEntity.status(200).body(courseService.getHoursByCourseCode(course_code ));

      }

}
