package com.duke.dls.service.impl;

import com.duke.dls.model.Student;
import com.duke.dls.model.StudentRequest;
import com.duke.dls.repo.StudentEntityRepository;
import com.duke.dls.service.StudentService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    StudentEntityRepository studentEntityRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentEntityRepository.findAllActive();
    }

    @Override
    public void insertStudent(StudentRequest request) {
        Student student = new Student();
        try {
            BeanUtils.copyProperties(student, request);
            student.setIsActive("Y");
            studentEntityRepository.saveAndFlush(student);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student updateStudent(StudentRequest request) {
        Student student = studentEntityRepository.findById(request.getStudentId()).isPresent() ? studentEntityRepository.findById(request.getStudentId()).get() : null;
        student.setAltEmail(request.getAltEmail());
        student.setDukeEmail(request.getDukeEmail());
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setNetId(request.getNetId());
        student.setProgramYear(request.getProgramYear());
        student.setPreferredName(request.getPreferredName());

        return studentEntityRepository.saveAndFlush(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentEntityRepository.deleteById(id);
    }

    @Override
    public void deactivateStudent(StudentRequest request) {
        Student student = studentEntityRepository.findById(request.getStudentId()).isPresent() ? studentEntityRepository.findById(request.getStudentId()).get() : null;
        student.setIsActive("N");
        studentEntityRepository.saveAndFlush(student);
    }
}
