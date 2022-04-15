package com.bsu.lab.AccountingSystem.controllers;

import com.bsu.lab.AccountingSystem.dao.HouseRepository;
import com.bsu.lab.AccountingSystem.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class HouseController {
    @Autowired
    HouseRepository houseRepository;
    @Autowired
    HouseService houseService;




}
