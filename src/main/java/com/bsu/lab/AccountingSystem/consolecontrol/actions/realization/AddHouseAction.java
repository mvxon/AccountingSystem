package com.bsu.lab.AccountingSystem.consolecontrol.actions.realization;


import com.bsu.lab.AccountingSystem.domain.Address;
import com.bsu.lab.AccountingSystem.domain.House;
import com.bsu.lab.AccountingSystem.service.HouseService;
import com.bsu.lab.AccountingSystem.consolecontrol.inputs.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddHouseAction {
    private final HouseService houseService;
    private final InputForEntrancesCount inputForEntrancesCount;
    private final InputForFloorsCount inputForFloorsCount;
    private final InputForFlatsCount inputForFlatsCount;
    private final InputForRoomsCount inputForRoomsCount;
    private final InputForRoomSquare inputForRoomSquare;

    @Autowired
    public AddHouseAction(HouseService houseService,
                          InputForEntrancesCount inputForEntrancesCount,
                          InputForFloorsCount inputForFloorsCount,
                          InputForFlatsCount inputForFlatsCount,
                          InputForRoomsCount inputForRoomsCount,
                          InputForRoomSquare inputForRoomSquare) {
        this.houseService = houseService;
        this.inputForEntrancesCount = inputForEntrancesCount;
        this.inputForFloorsCount = inputForFloorsCount;
        this.inputForFlatsCount = inputForFlatsCount;
        this.inputForRoomsCount = inputForRoomsCount;
        this.inputForRoomSquare = inputForRoomSquare;
    }

    public void execute() {
        int houseNumber = houseService.generateUniqueHouseNumber();
        int entrancesCount = inputForEntrancesCount.input();
        int floorsCount = inputForFloorsCount.input();
        int flatsPerFloor = inputForFlatsCount.input();
        List<List<Double>> squareOfRoomsOfFlats = new ArrayList<>();

        for (int i = 0; i < flatsPerFloor; i++) {
            int roomsCount = inputForRoomsCount.input(i + 1);
            List<Double> squareOfRoomsOfOneFlat = new ArrayList<>();
            System.out.println("Укажите площадь каждой комнаты: ");
            for (int j = 0; j < roomsCount; j++) {
                squareOfRoomsOfOneFlat.add(inputForRoomSquare.input(j + 1));
            }
            squareOfRoomsOfFlats.add(squareOfRoomsOfOneFlat);
        }
        House house;
        house = houseService.createHouse(houseNumber, entrancesCount, floorsCount, squareOfRoomsOfFlats);
        house.setAddress(Address.builder().city("Logoysk").street("Pobedy").build());
        houseService.finalStepSave(house);
        System.out.println("Дом номер " + houseNumber + " успешно добавлен!");
    }
}
