package com.jbeans.employee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.apache.logging.log4j.message.Message;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id()
   // @GeneratedValue(generator = "uuid",strategy = GenerationType.AUTO)
    @Column(name = "EEID")
    String EEID;
    @NotBlank(message="Name is Required Column ")
    @Size(min = 2, max = 100, message = "The length of full name must be between 2 and 100 characters.")
    String Full_Name;
    @NotBlank(message="Name is Required Column ")
    String Job_Title;
    @NotBlank(message="Name is Required Column ")
    String Department;
    String Business_Unit;
    String Gender;
    String Ethnicity;
    @NotNull(message = "The age is required.")
    @Min(value = 18, message = "The age must be equal or greater than 18")
    int Age;
    java.sql.Date Hire_Date;
    String Annual_Salary;
    double Bonus;
    String Country;
    String City;
}
