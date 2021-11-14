package com.bsu.lab.house;

import com.bsu.lab.util.SecuredNumbersScanner;


import java.util.ArrayList;
import java.util.List;

public class Entrance {
    private int entranceNumber;
    private int floorsCount;
    private int flatsPerFloor;
    private List<Floor> floors = new ArrayList<>();


    Entrance(int entranceNumber) {
        this.entranceNumber = entranceNumber;
        String question = "Введите количество этажей в доме(от 1 до 163): ";
        this.floorsCount = SecuredNumbersScanner.EnteringInfoCheck(question);
        if (this.floorsCount <= 0 || this.floorsCount > 163)
            do {
                System.out.println("Введено неверное значение...Повторите ввод");
                this.floorsCount = SecuredNumbersScanner.EnteringInfoCheck(question);
            } while (this.floorsCount <= 0 || this.floorsCount > 163);
        for (int i = 0; i < this.floorsCount; i++) {
            if (i == 0) {
                this.floors.add(new Floor(i)); // first floor creating
            } else {
                // copying first floor by copy constructor
                this.floors.add(new Floor(floors.get(0), i));
            }
            this.flatsPerFloor = floors.get(0).getFlatsPerFloor();
        }
    }
// copy constructor
    Entrance(Entrance entrance, int entranceNumber) {
        this.entranceNumber = entranceNumber;
        this.floorsCount = entrance.floorsCount;
        this.flatsPerFloor = entrance.flatsPerFloor;
        for (int i = 0; i < floorsCount; i++) {
            this.floors.add(new Floor(entrance.floors.get(0), i));
        }
    }

    public int getFloorsCount() {
        return floorsCount;
    }

    public int getFlatsPerFloor() {
        return flatsPerFloor;
    }

    public int getEntranceNumber() {
        return entranceNumber;
    }

    public List<Floor> getFloors() {
        return floors;
    }
}

