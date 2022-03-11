package com.bsu.lab.AccountingSystem.util.consolecontrol.inputs;

import com.bsu.lab.AccountingSystem.repository.HouseRepository;
import com.bsu.lab.AccountingSystem.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class SecuredNumbersScanner {
    private final HouseService houseService;
    private final HouseRepository houseRepository;

    @Autowired
    public SecuredNumbersScanner(HouseService houseService, HouseRepository houseRepository) {
        this.houseService = houseService;
        this.houseRepository = houseRepository;
    }

    public int enteringInfoCheck(String question) {
        int result = 0;
        Scanner numbersScanner = new Scanner(System.in);
        boolean numberFormat = true;
        System.out.print(question);
        do {
            if (!numberFormat) {
                System.out.print(question);
            }
            try {
                numberFormat = true;
                result = Integer.parseInt(numbersScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение...Повторите ввод");
                numberFormat = false;
            }
        } while (!numberFormat);
        return result;
    }

    public int enteringInfoCheckForHouseNumber() {
        int result = 0;
        Scanner numbersScanner = new Scanner(System.in);
        boolean numberFormatHouseCompareNumber = false;

        while (!numberFormatHouseCompareNumber) {
            String houseNumbers = "";
            for (Integer houseNumber : houseRepository.findUsedHouseNumbers()) {
                houseNumbers += houseNumber + ", ";
            }
            System.out.print("Введите номер нужного дома" + "(" + houseNumbers + "\b\b): ");

            try {
                numberFormatHouseCompareNumber = true;
                result = Integer.parseInt(numbersScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение");
                numberFormatHouseCompareNumber = false;
            }
            if (!houseRepository.findUsedHouseNumbers().contains(result)) {
                System.out.println("Введен номер несуществующего дома");
                numberFormatHouseCompareNumber = false;
                continue;
            }
        }
        return result;
    }

}

