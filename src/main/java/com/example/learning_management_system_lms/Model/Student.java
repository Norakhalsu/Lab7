package com.example.learning_management_system_lms.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    @NotNull(message = " Academic ID require ")
    private int academic_id;

    @NotEmpty(message = " Student Name Require ")
    private String name;

    @NotNull(message = " Student age require ")
    @Min(18)
    @Max(28)
    private int age;

    @NotEmpty(message = " Student major Require")
    private String major;

    @NotEmpty(message = " Student lecture Require")
    private String lecture;

    @Email(message = " Email must be email format ")
    @NotEmpty(message = " email is require ")
    private String academic_id_email;

    private int bonus_degree;
}
