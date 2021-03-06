package com.duke.dls.service.impl;

import com.duke.dls.constant.AppConstants;
import com.duke.dls.model.InventoryRequest;
import com.duke.dls.model.entity.Inventory;
import com.duke.dls.model.entity.InventoryHistory;
import com.duke.dls.model.entity.Student;
import com.duke.dls.model.entity.StudentHistory;
import com.duke.dls.repo.InventoryEntityRepository;
import com.duke.dls.repo.InventoryHistoryEntityRepository;
import com.duke.dls.repo.StudentEntityRepository;
import com.duke.dls.service.InventoryService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Autowired
    InventoryEntityRepository inventoryEntityRepository;

    @Autowired
    InventoryHistoryEntityRepository inventoryHistoryEntityRepository;

    @Autowired
    StudentEntityRepository studentEntityRepository;

    @Override
    public List<Inventory> getAllInventory() {
        return inventoryEntityRepository.findAllActive();
    }

    @Override
    public List<Inventory> getAllSpareInventory() {
        return inventoryEntityRepository.findAllSpare();
    }

    @Override
    public void insertInventory(InventoryRequest request) {
        Inventory inventory = new Inventory();
        try {
            BeanUtils.copyProperties(inventory, request);
            inventory.setIsActive(AppConstants.Y);
            inventory.setStatus(AppConstants.SPARE);
            inventory.setIscheckedout(AppConstants.N);
            inventory = inventoryEntityRepository.saveAndFlush(inventory);

            InventoryHistory inventoryHistory = InventoryHistory.builder().inventoryId(inventory.getInventoryId()).status(AppConstants.SPARE).comments("Inventory inserted with SN: " + inventory.getLaptopSn()).build();
            inventoryHistoryEntityRepository.saveAndFlush(inventoryHistory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Inventory updateInventory(InventoryRequest request) {
        Inventory inventory = inventoryEntityRepository.findById(request.getInventoryId()).isPresent() ? inventoryEntityRepository.findById(request.getInventoryId()).get() : null;
        inventory.setLaptopSn(request.getLaptopSn());
        inventory.setPowerAdapterSn(request.getPowerAdapterSn());
        inventory.setStatus(request.getStatus());
        return inventoryEntityRepository.saveAndFlush(inventory);
    }

    @Override
    public void deleteInventory(Long id) {
        inventoryEntityRepository.deleteById(id);
    }

    @Override
    public void deactivateInventory(InventoryRequest request) {
        Inventory inventory = inventoryEntityRepository.findById(request.getInventoryId()).isPresent() ? inventoryEntityRepository.findById(request.getInventoryId()).get() : null;
        inventory.setIsActive("N");
        inventoryEntityRepository.saveAndFlush(inventory);
    }

    @Override
    public void repairInventory(InventoryRequest request) {
        Inventory inventory = inventoryEntityRepository.findById(request.getInventoryId()).isPresent() ? inventoryEntityRepository.findById(request.getInventoryId()).get() : null;
        Student student = studentEntityRepository.findById(request.getStudentId()).isPresent() ? studentEntityRepository.findById(request.getStudentId()).get() : null;

        //unassigned inventory when sent for repair
        student.setInventory(null);
        studentEntityRepository.saveAndFlush(student);

        inventory.setStatus(request.getStatus());
        inventory.setIscheckedout(AppConstants.N);
        inventoryEntityRepository.saveAndFlush(inventory);

        InventoryHistory inventoryHistory = InventoryHistory.builder().inventoryId(request.getInventoryId()).comments(request.getComments()).status(request.getStatus()).build();
        inventoryHistoryEntityRepository.saveAndFlush(inventoryHistory);
    }

    @Override
    public Boolean isInventoryInUse(InventoryRequest request) {
        Inventory inventory = inventoryEntityRepository.findById(request.getInventoryId()).isPresent() ? inventoryEntityRepository.findById(request.getInventoryId()).get() : null;
        if (inventory.getStatus().equalsIgnoreCase("IN_USE")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<InventoryHistory> getAllInventoryHistory(Long inventoryId) {
        return inventoryHistoryEntityRepository.findAllInventoryHistoryOrdered(inventoryId);
    }

    @Override
    public void updateInventoryHistory(InventoryRequest request) {
        Inventory inventory = inventoryEntityRepository.findById(request.getInventoryId()).isPresent() ? inventoryEntityRepository.findById(request.getInventoryId()).get() : null;
        InventoryHistory inventoryHistory = InventoryHistory.builder().inventoryId(inventory.getInventoryId()).status(inventory.getStatus()).comments(request.getComments()).build();
        inventoryHistoryEntityRepository.saveAndFlush(inventoryHistory);
    }

}
