package com.duke.dls.controller;

import com.duke.dls.model.LaptopRequest;
import com.duke.dls.model.LaptopResponse;
import com.duke.dls.service.LaptopService;
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
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
@RequestMapping("/api/v1/laptop-controller")
public class LaptopController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LaptopController.class);

    @Autowired
    LaptopService laptopService;

    @GetMapping(value = "/getAllLaptops", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<LaptopResponse> getAllLaptops() {
        LaptopResponse laptopResponse = new LaptopResponse();
        laptopResponse.setLaptopList(laptopService.getAllLaptops());

        return ResponseEntity.ok(laptopResponse);
    }

    @PostMapping(value = "/insertLaptop", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> insertLaptop(@RequestBody LaptopRequest request) {
        laptopService.insertLaptop(request);
        return ResponseEntity.ok("Successful");
    }

    @PostMapping(value = "/updateLaptop", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> updateLaptop(@RequestBody LaptopRequest request) {
        laptopService.updateLaptop(request);
        return ResponseEntity.ok("Successful");
    }

    @DeleteMapping(value = "/deleteLaptop/{id}")
    public ResponseEntity<String> deleteLaptop(@PathVariable Long id) {
        laptopService.deleteLaptop(id);
        return ResponseEntity.ok("Successful");
    }

}


