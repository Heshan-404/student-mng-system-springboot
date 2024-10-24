package edu.icet.crm.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.entity.StudentEntity;
import edu.icet.crm.exceptions.NotFoundException;
import edu.icet.crm.exceptions.ZeroEntitiesException;
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
    public StudentEntity persist(StudentEntity student) {
        return jpaRepository.save(student);
    }

    @Override
    public List<StudentEntity> retrieveAll() {
        Iterable<StudentEntity> all = jpaRepository.findAll();
        List<StudentEntity> studentList = new ArrayList<>();
        all.forEach(studentList::add);
        if (studentList.isEmpty()) throw new ZeroEntitiesException("Not Found Any Student");
        return studentList;
    }

    @Override
    public StudentEntity retrieveStudentByStudentId(Integer studentId) {
        Optional<StudentEntity> studentEntity = jpaRepository.findById(studentId);
        if (studentEntity.isPresent()) return studentEntity.get();
        throw new NotFoundException("Student Not Found");
    }

    @Override
    public void remove(StudentEntity studentByStudentId) {
        jpaRepository.delete(studentByStudentId);
    }
}
