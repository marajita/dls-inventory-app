package com.duke.dls.service.impl;

import com.duke.dls.model.Laptop;
import com.duke.dls.model.LaptopRequest;
import com.duke.dls.repo.LaptopEntityRepository;
import com.duke.dls.service.LaptopService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class LaptopServiceImpl implements LaptopService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LaptopServiceImpl.class);

    @Autowired
    LaptopEntityRepository laptopEntityRepository;

    @Override
    public List<Laptop> getAllLaptops() {
        return laptopEntityRepository.findAll();
    }

    @Override
    public void insertLaptop(LaptopRequest request) {
        Laptop laptop = new Laptop();
        try {
            BeanUtils.copyProperties(laptop, request);
            laptopEntityRepository.saveAndFlush(laptop);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Laptop updateLaptop(LaptopRequest request) {
        Laptop laptop = laptopEntityRepository.findById(request.getId()).isPresent() ? laptopEntityRepository.findById(request.getId()).get() : null;
        laptop.setLaptopSn(request.getLaptopSn());
        laptop.setPowerAdapterSn(request.getPowerAdapterSn());


        return laptopEntityRepository.saveAndFlush(laptop);
    }

    @Override
    public void deleteLaptop(Long id) {
        laptopEntityRepository.deleteById(id);
    }
}
