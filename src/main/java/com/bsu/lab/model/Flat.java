package com.bsu.lab.model;

import com.bsu.lab.service.FlatService;
import com.bsu.lab.util.constants.GeneralConstants;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class Flat {
    @Setter
    private int residentsCount;
    @Setter
    private int roomsCount = 0;
    private int flatNumber;
    private static int flatNumberCounter;
    private final List<Room> rooms = new ArrayList<>();

    public Flat() {
        this.flatNumber = flatNumberCounter + 1;
        flatNumberCounter++;
        Room.nullifyRoomNumberCounter();
    }

    public Flat(@NotNull Flat flat) { // copy constructor for Flat
        this.flatNumber = flatNumberCounter + 1;
        flatNumberCounter++;
        Room.nullifyRoomNumberCounter();
        this.roomsCount = flat.roomsCount;
        this.residentsCount = (int) (Math.random() * (this.roomsCount - 1 + 1) + 1);
        for (int i = 0; i < this.roomsCount; i++) {
            this.rooms.add(new Room(flat.rooms.get(i)));
        }
    }

    public static void nullifyFlatNumberCounter() {
        flatNumberCounter = 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return Double.compare(FlatService.findFlatSquare(flat),FlatService.findFlatSquare(this)) == 0
                && roomsCount == flat.roomsCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(flatNumber, roomsCount);
    }


    @Override
    public String toString() {
        String result = "";
        result += "\n" + GeneralConstants.SEPARATION + "\n";
        result += "Номер квартиры: " + this.flatNumber +
                "\nКоличество комнат: " + this.roomsCount;
        for (int i = 0; i < this.roomsCount; i++) {
            result += "\nПлощадь " + (i + 1) + " комнаты: " + this.rooms.get(i).getRoomSquare();
        }
        result += "\nОбщая площадь квартиры: " + FlatService.findFlatSquare(this) +
                "\nКоличество жильцов: " + this.residentsCount;
        result += "\n" + GeneralConstants.SEPARATION + "\n";
        return result;
    }


}
