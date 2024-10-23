package edu.icet.crm.controllers;


import ch.qos.logback.core.util.StringUtil;
import edu.icet.crm.exceptions.InvalidParametersException;
import edu.icet.crm.model.IncompleteStudent;
import edu.icet.crm.model.Student;
import edu.icet.crm.model.SuccessResponse;
import edu.icet.crm.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequestMapping("/student")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3006")
public class StudentController {

    private static final String SUCCESS_MSG = "SUCCESS";
    private final StudentService studentService;

    @GetMapping(value = "/{studentId}")
    public Student get(@PathVariable("studentId") String studentId) {
        return studentService.getStudentByStudentId(studentId);
    }

    @PostMapping("")
    public SuccessResponse persist(@RequestBody Student student) {
        return SuccessResponse.builder().data(studentService.persist(student)).status(SUCCESS_MSG).build();
    }

    @GetMapping("/all")
    public SuccessResponse retrieveAllStudentList() {
        return SuccessResponse.builder().data(studentService.retrieveAll()).status(SUCCESS_MSG).build();
    }

    @PatchMapping("")
    public SuccessResponse patch(@RequestBody IncompleteStudent incompleteStudent) {
        return SuccessResponse.builder().data(
                studentService.patchUpdate(incompleteStudent)).status(SUCCESS_MSG).build();
    }

    @PutMapping("")
    public SuccessResponse update(@RequestBody Student student) {
        return SuccessResponse.builder().data(studentService.persist(student)).status(SUCCESS_MSG).build();
    }


    @DeleteMapping("/{studentId}")
    public SuccessResponse delete(@PathVariable String studentId) {
        studentService.remove(studentId);
        return SuccessResponse.builder().status(SUCCESS_MSG).build();
    }
}
