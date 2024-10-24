package edu.icet.crm.service;

import ch.qos.logback.core.util.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.entity.StudentEntity;
import edu.icet.crm.exceptions.InvalidParametersException;
import edu.icet.crm.model.IncompleteStudent;
import edu.icet.crm.model.Student;
import edu.icet.crm.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ObjectMapper objectMapper;


    @Override
    public Student persist(Student student) {
        if (!student.checkAllFieldsAreValid())
            throw new InvalidParametersException("Passed Invalid or empty parameters");
        return objectMapper.convertValue(
                studentRepository.persist(
                        objectMapper.convertValue(
                                student, StudentEntity.class)), Student.class);
    }

    @Override
    public List<Student> retrieveAll() {
        List<StudentEntity> studentEntities = studentRepository.retrieveAll();
        List<Student> studentList = new ArrayList<>();
        studentEntities.forEach(studentEntity ->
                studentList.add(
                        objectMapper.convertValue(studentEntity, Student.class)));
        return studentList;
    }

    @Override
    public Student getStudentByStudentId(String studentId) {
        int studentID;
        try {
            studentID = Integer.parseInt(studentId);
        } catch (Exception ex) {
            throw new InvalidParametersException("Invalid Student Id");
        }
        return objectMapper.convertValue(
                studentRepository.retrieveStudentByStudentId(studentID), Student.class);
    }

    @Override
    public void remove(String studentId) {

        Student studentByStudentId = getStudentByStudentId(studentId);

        studentRepository.remove(
                objectMapper.convertValue(studentByStudentId, StudentEntity.class));
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
