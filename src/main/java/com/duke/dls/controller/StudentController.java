package com.duke.dls.controller;

import com.duke.dls.model.StudentRequest;
import com.duke.dls.model.StudentResponse;
import com.duke.dls.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
@RequestMapping("/api/v1/student-controller")
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    @GetMapping(value = "/getAllStudents", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StudentResponse> getAllStudents() {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setStudentList(studentService.getAllStudents());

        return ResponseEntity.ok(studentResponse);
    }

    @PostMapping(value = "/insertStudent", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity insertStudent(@RequestBody StudentRequest request) {
        studentService.insertStudent(request);
        return ResponseEntity.ok(request);
    }

    @PostMapping(value = "/updateStudent", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity updateStudent(@RequestBody StudentRequest request) {
        studentService.updateStudent(request);
        return ResponseEntity.ok(request);

    }

    @PostMapping(value = "/deactivateStudent", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity deactivateStudent(@RequestBody StudentRequest request) {
        studentService.deactivateStudent(request);
        return ResponseEntity.ok(request);

    }

    @PostMapping(value = "/assignInventory", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity assignInventory(@RequestBody StudentRequest request) {
        studentService.assignInventory(request);
        return ResponseEntity.ok(request);

    }

    @PostMapping(value = "/updateStudentHistory", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity updateStudentHistory(@RequestBody StudentRequest request) {
        studentService.updateStudentHistory(request);
        return ResponseEntity.ok(request);

    }

    @DeleteMapping(value = "/deleteStudent/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

}


