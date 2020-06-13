package com.duke.dls.service;

import com.duke.dls.model.entity.Inventory;
import com.duke.dls.model.InventoryRequest;
import com.duke.dls.model.entity.InventoryHistory;

import java.util.List;

public interface InventoryService {

    public List<Inventory> getAllInventory();

    public List<Inventory> getAllSpareInventory();

    public void insertInventory(InventoryRequest request);

    public Inventory updateInventory(InventoryRequest request);

    public void deleteInventory(Long id);

    void deactivateInventory(InventoryRequest request);

    void repairInventory(InventoryRequest request);

    Boolean isInventoryInUse(InventoryRequest request);

    List<InventoryHistory> getAllInventoryHistory(Long inventoryId);

    void updateInventoryHistory(InventoryRequest request);
}
