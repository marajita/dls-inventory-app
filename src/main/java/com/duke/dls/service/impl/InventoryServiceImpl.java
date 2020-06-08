package com.duke.dls.service.impl;

import com.duke.dls.model.Inventory;
import com.duke.dls.model.InventoryRequest;
import com.duke.dls.repo.InventoryEntityRepository;
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
            inventory.setIsActive("Y");
            inventory.setStatus("SPARE");
            inventory.setIscheckedout("N");
            inventoryEntityRepository.saveAndFlush(inventory);
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
}
