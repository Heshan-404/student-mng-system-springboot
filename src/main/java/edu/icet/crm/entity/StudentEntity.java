package edu.icet.crm.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "student_table")
@ToString
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer studentId;
    private String name;
    private String age;
    private String address;
    private String guardianName;
    private String guardianContactNo;
}
