package com.example.learning_management_system_lms.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {

    @NotEmpty(message = " Course Number is Require ")
    @Pattern(regexp = "\\d+", message = " Course Number must be a Integers only")
    @Size(min = 4, max = 4 , message = "Course number must be 4 Integers only ")
    private String course_number;

    @NotEmpty(message = " Course Code is Require ")
    @Pattern(regexp = "^[a-zA-Z]*$", message = " Course Code Must contain only characters (no numbers). ")
    @Size(min = 3 , max = 3 , message = " Course Code must be 3 characters")
    private String course_code;

//    @Pattern(regexp = "^[a-zA-Z]*$", message = " Course Name Must contain only characters (no numbers). ")
    @NotEmpty(message = " Course Name is Require ")
    @Size(min = 3 , max = 15 , message = " course name length must be 3 -15 characters ")
    private String course_name;

    @PositiveOrZero(message = " number of Student must be Zero or positive numbers")
    private int number_of_student;

    @NotEmpty(message = "Course Day is Require ")
    private String Course_day;

    @NotNull(message = "Course Hours is Require ")
    @Positive(message = "Course Hours must be Positive Integers ")
    @Min(value = 1 , message = " Minimum Corse Hours is 1 hour ")
    private int course_hours;

}
