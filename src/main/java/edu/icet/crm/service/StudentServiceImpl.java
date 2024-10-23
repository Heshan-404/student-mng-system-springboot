package edu.icet.crm.service;

import ch.qos.logback.core.util.StringUtil;
import edu.icet.crm.exceptions.InvalidParametersException;
import edu.icet.crm.model.IncompleteStudent;
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
        if (!student.checkAllFieldsAreValid())
            throw new InvalidParametersException("Passed Invalid or empty parameters");
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
    public void remove(String studentId) {
        Student studentByStudentId = getStudentByStudentId(studentId);
        studentRepository.remove(studentByStudentId);
    }

    @Override
    public Student patchUpdate(IncompleteStudent incompleteStudent) {
        Student currentStudent = getStudentByStudentId(incompleteStudent.getStudentId());

        if (!StringUtil.isNullOrEmpty(incompleteStudent.getName())) {
            currentStudent.setName(incompleteStudent.getName());
        }
        if (!StringUtil.isNullOrEmpty(incompleteStudent.getAge())) {
            currentStudent.setAge(incompleteStudent.getAge());
        }
        if (!StringUtil.isNullOrEmpty(incompleteStudent.getAddress())) {
            currentStudent.setAddress(incompleteStudent.getAddress());
        }
        if (!StringUtil.isNullOrEmpty(incompleteStudent.getGuardianName())) {
            currentStudent.setGuardianName(incompleteStudent.getGuardianName());
        }
        if (!StringUtil.isNullOrEmpty(incompleteStudent.getGuardianContactNo())) {
            currentStudent.setGuardianContactNo(incompleteStudent.getGuardianContactNo());
        }
        return persist(currentStudent);
    }
}
