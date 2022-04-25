package com.bsu.lab.AccountingSystem.util.consolecontrol.actions.realization;


import com.bsu.lab.AccountingSystem.domain.House;
import com.bsu.lab.AccountingSystem.dao.HouseRepository;
import com.bsu.lab.AccountingSystem.service.HouseService;
import com.bsu.lab.AccountingSystem.service.HouseServiceImpl;
import com.bsu.lab.AccountingSystem.util.consolecontrol.inputs.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddHouseAction {
    private final HouseService houseService;
    private final HouseRepository houseRepository;
    private final InputForEntrancesCount inputForEntrancesCount;
    private final InputForFloorsCount inputForFloorsCount;
    private final InputForFlatsCount inputForFlatsCount;
    private final InputForRoomsCount inputForRoomsCount;
    private final InputForRoomSquare inputForRoomSquare;

    @Autowired
    public AddHouseAction(HouseService houseService,
                          HouseRepository houseRepository,
                          InputForEntrancesCount inputForEntrancesCount,
                          InputForFloorsCount inputForFloorsCount,
                          InputForFlatsCount inputForFlatsCount,
                          InputForRoomsCount inputForRoomsCount,
                          InputForRoomSquare inputForRoomSquare) {
        this.houseService = houseService;
        this.houseRepository = houseRepository;
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
        List<ArrayList<Double>> squareOfRoomsOfFlats = new ArrayList<>();

        for (int i = 0; i < flatsPerFloor; i++) {
            int roomsCount = inputForRoomsCount.input(i + 1);
            ArrayList<Double> squareOfRoomsOfOneFlat = new ArrayList<>();
            System.out.println("Укажите площадь каждой комнаты: ");
            for (int j = 0; j < roomsCount; j++) {
                squareOfRoomsOfOneFlat.add(inputForRoomSquare.input(j + 1));
            }
            squareOfRoomsOfFlats.add(squareOfRoomsOfOneFlat);
        }
        House house;
        house = houseService.createHouse(houseNumber, entrancesCount, floorsCount, squareOfRoomsOfFlats);
        System.out.println("Дом номер " + houseNumber + " успешно добавлен!");
    }
}
