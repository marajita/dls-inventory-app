package com.duke.dls.controller;

import com.duke.dls.model.StudentRequest;
import com.duke.dls.model.StudentResponse;
import com.duke.dls.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    @GetMapping("/api/v1/student-controller/getAllStudents")
    public ResponseEntity<StudentResponse> getAllStudents() {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setStudentList(studentService.getAllStudents());

        return ResponseEntity.ok(studentResponse);
    }

    @PostMapping(value = "/api/v1/student-controller/insertStudent", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> insertStudent(@RequestBody StudentRequest request) {
        studentService.insertStudent(request);
        return ResponseEntity.ok("Successful");
    }

    @PostMapping(value = "/api/v1/student-controller/updateStudent", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> updateStudent(@RequestBody StudentRequest request) {
        studentService.updateStudent(request);
        return ResponseEntity.ok("Successful");
    }

}


