package com.duke.dls.controller;

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

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
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
    public ResponseEntity<String> insertInventory(@RequestBody InventoryRequest request) {
        inventoryService.insertInventory(request);
        return ResponseEntity.ok("Successful");
    }

    @PostMapping(value = "/updateInventory", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> updateInventory(@RequestBody InventoryRequest request) {
        inventoryService.updateInventory(request);
        return ResponseEntity.ok("Successful");
    }

    @PostMapping(value = "/deactivateInventory", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity deactivateInventory(@RequestBody InventoryRequest request) {
        inventoryService.deactivateInventory(request);
        return ResponseEntity.ok(request);

    }

    @DeleteMapping(value = "/deleteInventory/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok("Successful");
    }

}


