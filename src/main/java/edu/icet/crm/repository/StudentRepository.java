package edu.icet.crm.repository;

import edu.icet.crm.model.Student;

import java.util.List;

public interface StudentRepository {
    Student persist(Student student);

    List<Student> retrieveAll();

    Student retrieveStudentByStudentId(String studentId);

    void remove(Student studentByStudentId);
}
