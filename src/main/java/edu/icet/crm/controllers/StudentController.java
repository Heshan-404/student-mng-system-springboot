package edu.icet.crm.controllers;


import ch.qos.logback.core.util.StringUtil;
import edu.icet.crm.exceptions.InvalidParametersException;
import edu.icet.crm.model.IncompleteStudent;
import edu.icet.crm.model.Student;
import edu.icet.crm.model.SuccessResponse;
import edu.icet.crm.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequestMapping("/student")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3006")
public class StudentController {

    private static final String SUCCESS_MSG = "SUCCESS";
    private final StudentService studentService;

    @GetMapping("/{studentId}")
    public Student get(@PathVariable String studentId) {
        return studentService.getStudentByStudentId(studentId);
    }

    @PostMapping("")
    public SuccessResponse persist(@RequestBody Student student) {
        if (!student.checkAllFieldsAreValid())
            throw new InvalidParametersException("Passed Invalid or empty parameters");
        return SuccessResponse.builder().data(studentService.persist(student)).status(SUCCESS_MSG).build();
    }

    @GetMapping("/all")
    public SuccessResponse retrieveAllStudentList() {
        return SuccessResponse.builder().data(studentService.retrieveAll()).status(SUCCESS_MSG).build();
    }

    @PatchMapping("")
    public SuccessResponse patch(@RequestBody IncompleteStudent incompleteStudent) {
        if (StringUtil.isNullOrEmpty(incompleteStudent.getStudentId()))
            throw new InvalidParametersException("Something wrong with student id");

        Student currentStudent = studentService.getStudentByStudentId(incompleteStudent.getStudentId());

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

        return SuccessResponse.builder().data(
                studentService.persist(currentStudent)).status(SUCCESS_MSG).build();
    }

    @PutMapping("")
    public SuccessResponse update(@RequestBody Student student) {
        if (!student.checkAllFieldsAreValid())
            throw new InvalidParametersException("Passed Invalid or empty parameters");
        return SuccessResponse.builder().data(studentService.persist(student)).status(SUCCESS_MSG).build();
    }


    @DeleteMapping("/{studentId}")
    public SuccessResponse delete(@PathVariable String studentId) {
        Student studentByStudentId = studentService.getStudentByStudentId(studentId);
        studentService.remove(studentByStudentId);
        return SuccessResponse.builder().status(SUCCESS_MSG).build();
    }
}
