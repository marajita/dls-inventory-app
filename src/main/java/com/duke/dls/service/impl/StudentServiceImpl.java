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
        return studentEntityRepository.findAll();
    }

    @Override
    public void insertStudent(StudentRequest request) {
        Student student = new Student();
        try {
            BeanUtils.copyProperties(student, request);
            studentEntityRepository.saveAndFlush(student);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student updateStudent(StudentRequest request) {
        Student student = studentEntityRepository.findById(request.getId()).isPresent() ? studentEntityRepository.findById(request.getId()).get() : null;
        student.setAltEmail(request.getAltEmail());
        student.setEmail(request.getEmail());
        student.setFirstName(request.getFirstName());
        student.setLaptopSn(request.getLaptopSn());
        student.setLastName(request.getLastName());
        student.setNetId(request.getNetId());
        student.setPowerAdapterSn(request.getPowerAdapterSn());
        student.setProgramYear(request.getProgramYear());

        return studentEntityRepository.saveAndFlush(student);
    }
}
