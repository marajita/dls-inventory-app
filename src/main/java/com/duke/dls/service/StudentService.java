package com.duke.dls.service;

import com.duke.dls.model.Student;
import com.duke.dls.model.StudentRequest;

import java.util.List;

public interface StudentService {

    public List<Student> getAllStudents();

    public void insertStudent(StudentRequest request);

    public Student updateStudent(StudentRequest request);

    public void deleteStudent(Long id);
}
