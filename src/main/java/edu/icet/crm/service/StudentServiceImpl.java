package edu.icet.crm.service;

import edu.icet.crm.model.Student;
import edu.icet.crm.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student persist(Student student) {
        return studentRepository.persist(student);
    }

    @Override
    public List<Student> retrieveAll() {
        return studentRepository.retrieveAll();
    }

    @Override
    public Student getStudentByStudentId(String studentId) {
        return studentRepository.retrieveStudentByStudentId(studentId);
    }

    @Override
    public void remove(Student studentByStudentId) {
        studentRepository.remove(studentByStudentId);
    }

}
