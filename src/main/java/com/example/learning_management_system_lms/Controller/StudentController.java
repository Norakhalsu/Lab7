package com.example.learning_management_system_lms.Controller;


import com.example.learning_management_system_lms.Model.Student;
import com.example.learning_management_system_lms.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;


      @GetMapping("/get") // get all student
    public ResponseEntity getStudentService() {
        ArrayList<Student> students = studentService.getStudents();
        return ResponseEntity.status(200).body(students);
    }

    @PostMapping("/add")// add new Student
    public ResponseEntity addStudent(@Valid @RequestBody Student student , Errors errors) {
          if (errors.hasErrors()) {
              String message = errors.getFieldError().getDefaultMessage();
              return ResponseEntity.status(400).body(message);
          }
          studentService.addStudents(student);
          return ResponseEntity.status(201).body("Student added successfully");
    }

    @PutMapping("/update/{academic_id}")// update student by Academic Id
    public ResponseEntity updateStudent(@PathVariable int academic_id, @Valid @RequestBody Student student , Errors errors) {
          if (errors.hasErrors()) {
              String message = errors.getFieldError().getDefaultMessage();
              return ResponseEntity.status(400).body(message);
          }
       if (studentService.updateStudent(academic_id,student)) {
            return ResponseEntity.status(400).body(" Student Updated successfully");
       }
       return ResponseEntity.status(200).body("Sorry Academic id does not match");
    }

    @DeleteMapping("/delete/{academic_id}")// delete student by academic id
    public ResponseEntity deleteStudent(@PathVariable int academic_id) {

          if (studentService.deleteStudent(academic_id)){
              return ResponseEntity.status(200).body("Student deleted successfully");
          }
          return ResponseEntity.status(200).body(" sorry Student not found");
    }


     @GetMapping("/major/{major}")// take major and return students in this major
    public ResponseEntity getStudentInMajor(@PathVariable String  major) {
       ArrayList<Student> MajorList = studentService.getStudentsByMajor(major);

       if (MajorList.isEmpty()){
           return ResponseEntity.status(404).body("Students not found");
       }
       return ResponseEntity.status(200).body(MajorList);
     }


     @PutMapping("bonus/{lecture}")// take lecture and add 1 bonus
    public ResponseEntity updateStudentInBonus(@PathVariable String  lecture) {

           if(studentService.addBonus(lecture)){
               return ResponseEntity.status(200).body("Add Bonus successfully");
           }
           return ResponseEntity.status(200).body("Add Bonus failed ");
     }


     @GetMapping("/check-graduated/{academic_id}")
     public ResponseEntity<String> checkNotGraduated(@PathVariable int academic_id) {

         boolean graduationStatus = studentService.checkNotGraduated(academic_id);

         if (graduationStatus) {
             return ResponseEntity.status(200).body("Student has not graduated yet");
         }
             return ResponseEntity.status(400).body("Student has graduated");

     }



     // update Academic email Of Student
     @PutMapping("/update-email/{academic_email}/{email}") // get Student by email
     public ResponseEntity updateStudentEmail( @PathVariable String academic_email, @PathVariable String email) {
           boolean student = studentService.updateEmail(academic_email ,email);

           if(student){
               return ResponseEntity.status(200).body("Student Email updated successfully");
           }
           return ResponseEntity.status(200).body(" Not Found Student Email");
     }


}
