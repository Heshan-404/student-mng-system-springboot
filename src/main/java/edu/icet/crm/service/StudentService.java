package edu.icet.crm.service;

import edu.icet.crm.model.Student;

import java.util.List;

public interface StudentService {
    Student persist(Student student);

    List<Student> retrieveAll();

    Student getStudentByStudentId(String studentId);

    void remove(Student studentByStudentId);
}
