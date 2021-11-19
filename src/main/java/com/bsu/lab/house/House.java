package com.bsu.lab.house;

import com.bsu.lab.service.HouseService;
import com.bsu.lab.util.constants.GeneralConstants;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class House {
    private static int currentHouseNumber;
    private final int houseNumber;
    @Setter
    private int entrancesCount = 0;
    private final List<Entrance> entrances = new ArrayList<>();

    public House() {
        this.houseNumber = currentHouseNumber + 1;
        Entrance.NullifyEntranceNumberCounter();
        Flat.nullifyFlatNumberCounter();
        currentHouseNumber++;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        House house = (House) o;
        return this.entrances.get(0).getFloorsCount() == house.entrances.get(0).getFloorsCount()
                && this.entrances.get(0).getFloors().get(0).getFlatsPerFloor() ==
                house.entrances.get(0).getFloors().get(0).getFlatsPerFloor()
                && HouseService.totalHouseSquare(this) == HouseService.totalHouseSquare(house);
    }

    public String toString() {
        return "\n" + GeneralConstants.SEPARATION +
                "\nНомер дома: " + (this.houseNumber) +
                "\nКоличество подъездов: " + (this.entrancesCount) +
                "\nКоличество этажей: " + (this.entrances.get(0).getFloorsCount()) +
                "\nКоличество квартир на одном этаже: " + this.entrances.get(0).getFloors().get(0).getFlatsPerFloor() +
                "\nОбщее количество квартир: " + HouseService.getFlatsCount(this) +
                "\nОбщая площадь дома: " + (HouseService.totalHouseSquare(this)) +
                "\nОбщее количество жильцов: " + (HouseService.totalHouseResidentsCount(this)) +
                "\n" + GeneralConstants.SEPARATION + "\n";
    }


}
