package edu.icet.crm.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.entity.StudentEntity;
import edu.icet.crm.exceptions.InvalidParametersException;
import edu.icet.crm.exceptions.NotFoundException;
import edu.icet.crm.exceptions.ZeroEntitiesException;
import edu.icet.crm.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {
    private final ObjectMapper objectMapper;
    private final JpaRepository jpaRepository;

    @Override
    public Student persist(Student student) {
        return objectMapper.convertValue(jpaRepository.save(
                objectMapper.convertValue(student, StudentEntity.class)), Student.class);
    }

    @Override
    public List<Student> retrieveAll() {
        Iterable<StudentEntity> all = jpaRepository.findAll();
        List<Student> studentList = new ArrayList<>();
        all.forEach(studentEntity -> studentList.add(
                objectMapper.convertValue(studentEntity, Student.class)));
        if (studentList.isEmpty()) throw new ZeroEntitiesException("Not Found Any Student");
        return studentList;
    }

    @Override
    public Student retrieveStudentByStudentId(String studentId) {
        int studentID;
        try {
            studentID = Integer.parseInt(studentId);
        } catch (Exception ex) {
            throw new InvalidParametersException("Invalid Student Id");
        }
        Optional<StudentEntity> studentEntity = jpaRepository.findById(studentID);
        if (studentEntity.isPresent()) return objectMapper.convertValue(studentEntity, Student.class);
        throw new NotFoundException("Student Not Found");
    }

    @Override
    public void remove(Student studentByStudentId) {
        jpaRepository.delete(objectMapper.convertValue(studentByStudentId,StudentEntity.class));
    }
}
