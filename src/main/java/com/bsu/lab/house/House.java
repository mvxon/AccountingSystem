package com.bsu.lab.house;

import com.bsu.lab.service.HouseService;
import com.bsu.lab.util.SecuredNumbersScanner;

import java.util.*;

public class House {
    private static int currentHouseNumber;
    private final int houseNumber;
    private int entrancesCount;
    private int flatsCount;
    private List<Entrance> entrances = new ArrayList<>();

    public House() {
        this.houseNumber = currentHouseNumber + 1;
        String question = "Введите количество подъездов в доме(от 1 до 20): ";
        this.entrancesCount = SecuredNumbersScanner.EnteringInfoCheck(question);
        if (this.entrancesCount <= 0 || this.entrancesCount > 20)
            do {
                System.out.println("Введено неверное значение...Повторите ввод");
                this.entrancesCount = SecuredNumbersScanner.EnteringInfoCheck(question);
            } while (this.entrancesCount <= 0 || this.entrancesCount > 20);
        for (int i = 0; i < this.entrancesCount; i++) {
            if (i == 0) {
                this.entrances.add(new Entrance(i)); // creating first entrance
            } else {
                // copying first entrance by copy constructor
                this.entrances.add(new Entrance(entrances.get(0), i));
            }
        }
        this.flatsCount = HouseService.getFlatsCount(this);
        System.out.println("Дом номер " + (currentHouseNumber + 1) + " успешно добавлен!");
        currentHouseNumber++;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        House house = (House) o;
        return this.entrances.get(0).getFloorsCount() == house.entrances.get(0).getFloorsCount()
                && this.entrances.get(0).getFlatsPerFloor() == house.entrances.get(0).getFlatsPerFloor()
                && HouseService.totalHouseSquare(this) == HouseService.totalHouseSquare(house);
    }

    public String toString() {
        return "\n--------------------------------------------------------------------------------\n" +
                "\nНомер дома: " + (this.houseNumber) +
                "\nКоличество подъездов: " + (this.entrancesCount) +
                "\nКоличество этажей: " + (this.entrances.get(0).getFloorsCount()) +
                "\nКоличество квартир на одном этаже: " + this.entrances.get(0).getFlatsPerFloor() +
                "\nОбщее количество квартир: " + HouseService.getFlatsCount(this) +
                "\nОбщая площадь дома: " + (HouseService.totalHouseSquare(this)) +
                "\nОбщее количество жильцов: " + (HouseService.totalHouseResidentsCount(this)) +
                "\n--------------------------------------------------------------------------------\n";
    }

    public int getEntrancesCount() {
        return this.entrancesCount;
    }

    public List<Entrance> getEntrances() {
        return this.entrances;
    }

    public int getHouseNumber() {
        return this.houseNumber;
    }

    public int getFlatsCount() {
        return this.flatsCount;
    }


}
