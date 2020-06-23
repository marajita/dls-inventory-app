package com.duke.dls.service.impl;

import com.duke.dls.constant.AppConstants;
import com.duke.dls.model.StudentRequest;
import com.duke.dls.model.entity.Inventory;
import com.duke.dls.model.entity.InventoryHistory;
import com.duke.dls.model.entity.Student;
import com.duke.dls.model.entity.StudentHistory;
import com.duke.dls.repo.InventoryEntityRepository;
import com.duke.dls.repo.InventoryHistoryEntityRepository;
import com.duke.dls.repo.StudentEntityRepository;
import com.duke.dls.repo.StudentHistoryEntityRepository;
import com.duke.dls.service.StudentService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    StudentEntityRepository studentEntityRepository;

    @Autowired
    StudentHistoryEntityRepository studentHistoryEntityRepository;

    @Autowired
    InventoryEntityRepository inventoryEntityRepository;

    @Autowired
    InventoryHistoryEntityRepository inventoryHistoryEntityRepository;

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
            student = studentEntityRepository.saveAndFlush(student);
            StudentHistory studentHistory = StudentHistory.builder().studentId(student.getStudentId()).netId(student.getNetId()).comments("Student inserted with Net ID: " + student.getNetId()).build();
            studentHistoryEntityRepository.saveAndFlush(studentHistory);
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
        student.setIsActive(AppConstants.N);
        studentEntityRepository.saveAndFlush(student);
    }

    @Override
    public void assignInventory(StudentRequest request) {
        Student student = studentEntityRepository.findById(request.getStudentId()).isPresent() ? studentEntityRepository.findById(request.getStudentId()).get() : null;
        Inventory inventory = inventoryEntityRepository.findById(request.getInventoryId()).isPresent() ? inventoryEntityRepository.findById(request.getInventoryId()).get() : null;

        //unassign existing inventory if present
        if (student.getInventory() != null) {
            Inventory inventoryCurrent = student.getInventory();
            inventoryCurrent.setIscheckedout(AppConstants.N);
            inventoryCurrent.setStatus(AppConstants.SPARE);
            inventoryEntityRepository.saveAndFlush(inventoryCurrent);

            InventoryHistory inventoryHistory = InventoryHistory.builder().status(inventoryCurrent.getStatus()).inventoryId(inventoryCurrent.getInventoryId()).comments("Inventory unassigned from student - Net ID:  " + student.getNetId()).build();
            inventoryHistoryEntityRepository.saveAndFlush(inventoryHistory);
        }

        student.setInventory(inventory);
        studentEntityRepository.saveAndFlush(student);

        StudentHistory studentHistory = StudentHistory.builder().studentId(student.getStudentId()).netId(student.getNetId()).comments("Inventory assigned - Laptop SN: " + inventory.getLaptopSn()).build();
        studentHistoryEntityRepository.saveAndFlush(studentHistory);

        inventory.setIscheckedout(AppConstants.Y);
        inventory.setStatus(AppConstants.IN_USE);
        inventoryEntityRepository.saveAndFlush(inventory);

        InventoryHistory inventoryHistory = InventoryHistory.builder().status(AppConstants.IN_USE).inventoryId(inventory.getInventoryId()).comments("Inventory assigned to student - Net ID:  " + student.getNetId()).build();
        inventoryHistoryEntityRepository.saveAndFlush(inventoryHistory);
    }

    @Override
    public void updateStudentHistory(StudentRequest request) {
        Student student = studentEntityRepository.findById(request.getStudentId()).isPresent() ? studentEntityRepository.findById(request.getStudentId()).get() : null;
        StudentHistory studentHistory = StudentHistory.builder().studentId(request.getStudentId()).netId(student.getNetId()).comments(request.getComments()).build();
        studentHistoryEntityRepository.saveAndFlush(studentHistory);
    }

    @Override
    public List<StudentHistory> getAllStudentHistory(Long studentId) {
        return studentHistoryEntityRepository.findAllStudentHistoryOrdered(studentId);
    }

    @Override
    public Student getStudentByInventoryId(Long inventoryId) throws Exception {
        try {
            return studentEntityRepository.getStudentByInventoryId(inventoryId);
        } catch (NonUniqueResultException e) {
            throw new Exception(e);
        }
    }

    @Override
    public void updateStudentFromUpload(StudentRequest request) {
        Student student = studentEntityRepository.findStudentByNetIdAndIsActive(request.getNetId(), "Y");
        // insert student if not found.
        if (student == null) {
            insertStudent(request);
            student = studentEntityRepository.findStudentByNetIdAndIsActive(request.getNetId(), "Y");
        }
        Inventory inventory = inventoryEntityRepository.findInventoryByLaptopSn(request.getLaptopSn());

        student.setInventory(inventory);
        studentEntityRepository.saveAndFlush(student);

        if (inventory != null) {
            StudentHistory studentHistory = StudentHistory.builder().studentId(student.getStudentId()).netId(student.getNetId()).comments("Inventory assigned - Laptop SN: " + inventory.getLaptopSn()).build();
            studentHistoryEntityRepository.saveAndFlush(studentHistory);

            inventory.setIscheckedout(AppConstants.Y);
            inventory.setStatus(AppConstants.IN_USE);
            inventoryEntityRepository.saveAndFlush(inventory);

            InventoryHistory inventoryHistory = InventoryHistory.builder().status(AppConstants.IN_USE).inventoryId(inventory.getInventoryId()).comments("Inventory assigned to student - Net ID:  " + student.getNetId()).build();
            inventoryHistoryEntityRepository.saveAndFlush(inventoryHistory);
        }
    }
}
