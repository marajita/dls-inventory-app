package com.duke.dls.controller;

import com.duke.dls.model.InventoryHistoryResponse;
import com.duke.dls.model.InventoryRequest;
import com.duke.dls.model.InventoryResponse;
import com.duke.dls.service.InventoryService;
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

import java.util.List;

@CrossOrigin(origins = "https://dls-inventory.herokuapp.com", maxAge = 3600)
@Controller
@RequestMapping("/api/v1/inventory-controller")
public class InventoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    InventoryService inventoryService;

    @GetMapping(value = "/getAllInventory", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<InventoryResponse> getAllInventory() {
        InventoryResponse laptopResponse = new InventoryResponse();
        laptopResponse.setInventoryList(inventoryService.getAllInventory());

        return ResponseEntity.ok(laptopResponse);
    }

    @GetMapping(value = "/getAllSpareInventory", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<InventoryResponse> getAllSpareInventory() {
        InventoryResponse laptopResponse = new InventoryResponse();
        laptopResponse.setInventoryList(inventoryService.getAllSpareInventory());

        return ResponseEntity.ok(laptopResponse);
    }

    @PostMapping(value = "/insertInventory", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity insertInventory(@RequestBody InventoryRequest request) {
        inventoryService.insertInventory(request);
        return ResponseEntity.ok(request);
    }

    @PostMapping(value = "/updateInventory", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity updateInventory(@RequestBody InventoryRequest request) {
        inventoryService.updateInventory(request);
        return ResponseEntity.ok(request);
    }

    @PostMapping(value = "/deactivateInventory", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity deactivateInventory(@RequestBody InventoryRequest request) {
        inventoryService.deactivateInventory(request);
        return ResponseEntity.ok(request);

    }

    @PostMapping(value = "/repairInventory", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity repairInventory(@RequestBody InventoryRequest request) {
        inventoryService.repairInventory(request);
        return ResponseEntity.ok(request);

    }

    @PostMapping(value = "/isInventoryInUse", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> isInventoryInUse(@RequestBody InventoryRequest request) {
        Boolean val = inventoryService.isInventoryInUse(request);
        return ResponseEntity.ok(val);

    }

    @PostMapping(value = "/updateInventoryHistory", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity updateInventoryHistory(@RequestBody InventoryRequest request) {
        inventoryService.updateInventoryHistory(request);
        return ResponseEntity.ok(request);

    }

    @GetMapping(value = "/getAllInventoryHistory/{inventoryId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<InventoryHistoryResponse> getAllStudentHistory(@PathVariable Long inventoryId) {
        InventoryHistoryResponse inventoryHistoryResponse = new InventoryHistoryResponse();
        inventoryHistoryResponse.setInventoryHistoryList(inventoryService.getAllInventoryHistory(inventoryId));
        return ResponseEntity.ok(inventoryHistoryResponse);
    }

    @PostMapping(value = "/insertInventoryFromUpload", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity insertInventoryFromUpload(@RequestBody List<InventoryRequest> requestList) {
        for (InventoryRequest request : requestList) {
            inventoryService.insertInventory(request);
        }
        return ResponseEntity.ok(requestList);
    }

    @DeleteMapping(value = "/deleteInventory/{id}")
    public ResponseEntity deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok(id);
    }

}


