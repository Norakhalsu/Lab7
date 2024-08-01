package com.example.learning_management_system_lms.Service;


import com.example.learning_management_system_lms.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {

    ArrayList<Student> students = new ArrayList<>();

    // get all student
    public ArrayList<Student> getStudents() {
        return students;
    }

    // add new Student
    public void addStudents(Student student) {
        students.add(student);
    }

    // update student by academic id
    public boolean updateStudent(int academic_id,Student student) {
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getAcademic_id()== academic_id){
                students.set(i, student);
                return true;
            }
        }
        return false;
    }


    // delete student by academic id
    public boolean deleteStudent(int academic_id) {
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getAcademic_id()== academic_id){
                students.remove(i);
                return true;
            }
        }
        return false;
    }


      // take major and return  student
      public ArrayList<Student> getStudentsByMajor(String major) {
        ArrayList<Student> MajorList = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getMajor().equalsIgnoreCase(major)){
                MajorList.add(students.get(i));
            }
        }
        return MajorList;
      }


      // take lecture and add 1 bonus
        public boolean addBonus(String lecture) {
         for(Student student : students){
             if(student.getLecture().equalsIgnoreCase(lecture)){
                 student.setBonus_degree(student.getBonus_degree()+1);
                 return true;
             }
         }
         return false;
        }

         // take age and check if more than  24 is graduated
        public boolean checkNotGraduated(int academic_id ){
        for(Student student : students){
            if(student.getAcademic_id() == academic_id){
              if(student.getAge()<= 24){
                return true;
            }
           }
          }
        return false;
        }

        // update Academic Email Of Student
        public boolean updateEmail( String academic_email ,String email) {
        for(Student student : students){
            if (student.getAcademic_id_email().equalsIgnoreCase(academic_email)){
                student.setAcademic_id_email(email);
                return true;
            }
        }
        return false;
        }





}
