package com.duke.dls.service;

import com.duke.dls.model.Laptop;
import com.duke.dls.model.LaptopRequest;

import java.util.List;

public interface LaptopService {

    public List<Laptop> getAllLaptops();

    public void insertLaptop(LaptopRequest request);

    public Laptop updateLaptop(LaptopRequest request);

    public void deleteLaptop(Long id);
}
