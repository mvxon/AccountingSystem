package com.bsu.lab.house;

import com.bsu.lab.util.SecuredNumbersScanner;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private int floorNumber;
    private List<Flat> flats = new ArrayList<>();
    private static int flatsPerFloor;

   public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        String question = "Введите количество квартир на одном этаже(от 1 до 20): ";
        this.flatsPerFloor = SecuredNumbersScanner.EnteringInfoCheck(question);
        if (this.flatsPerFloor <= 0 || this.flatsPerFloor > 20)
            do {
                System.out.println("Введено неверное значение...Повторите ввод");
                this.flatsPerFloor = SecuredNumbersScanner.EnteringInfoCheck(question);
            } while (this.flatsPerFloor <= 0 || this.flatsPerFloor > 20);
        if (floorNumber == 0) {
            for (int i = 0; i < flatsPerFloor; i++) {
                this.flats.add(new Flat(i+1)); // flats creating
            }
        }
    }
 // copy constructor
    Floor(Floor floor, int floorNumber) {
        this.floorNumber = floorNumber;
        for (int i = 0; i < flatsPerFloor; i++) {
            this.flats.add(new Flat(floor.flats.get(i)));
        }
    }

    public int getFloorNumber() {
        return this.floorNumber;
    }

    public int getFlatsPerFloor() {
        return flatsPerFloor;
    }

    public List<Flat> getFlats() {
        return flats;
    }
}
