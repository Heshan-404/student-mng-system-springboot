package edu.icet.crm.repository;

import edu.icet.crm.entity.StudentEntity;

import java.util.List;

public interface StudentRepository {
    StudentEntity persist(StudentEntity student);

    List<StudentEntity> retrieveAll();

    StudentEntity retrieveStudentByStudentId(Integer studentId);

    void remove(StudentEntity studentByStudentId);
}
