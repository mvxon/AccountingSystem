package com.bsu.lab.house;

import com.bsu.lab.service.FlatService;
import com.bsu.lab.util.SecuredNumbersScanner;
import com.bsu.lab.util.constants.GeneralConstants;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Flat {
    private double flatSquare;
    private int residentsCount;
    private int roomsCount;
    private int flatUniqueNumber;
    private static int flatNumberCounter;
    private final List<Room> rooms = new ArrayList<>();

    public Flat() {
        this.flatUniqueNumber = flatNumberCounter + 1;
        flatNumberCounter++;
        Room.nullifyRoomNumberCounter();
    }

    Flat(@NotNull Flat flat) { // copy constructor for Flat
        this.flatUniqueNumber = flatNumberCounter + 1;
        flatNumberCounter++;
        Room.nullifyRoomNumberCounter();
        this.roomsCount = flat.roomsCount;
        this.flatSquare = flat.flatSquare;
        this.residentsCount = (int) (Math.random() * (this.roomsCount - 1 + 1) + 1);
        for (int i = 0; i < this.roomsCount; i++) {
            this.rooms.add(new Room(flat.rooms.get(i)));
        }
    }

    public static void nullifyFlatNumberCounter() {
        flatNumberCounter = 0;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public int getRoomsCount() {
        return this.roomsCount;
    }

    public List<Room> getRooms() {
        return this.rooms;
    }

    public void setFlatSquare(double square) {
        this.flatSquare = square;
    }

    public int getFlatUniqueNumber() {
        return this.flatUniqueNumber;
    }

    public int getResidentsCount() {
        return this.residentsCount;
    }

    public void setResidentsCount(int residentsCount) {
        this.residentsCount = residentsCount;
    }

    public double getFlatSquare() {
        return flatSquare;
    }

    public void setRoomsCount(int roomsCount) {
        this.roomsCount = roomsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return Double.compare(flat.flatSquare, flatSquare) == 0 && roomsCount == flat.roomsCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(flatSquare, roomsCount);
    }


    @Override
    public String toString() {
        String result = "";
        result += "\n" + GeneralConstants.separation + "\n";
        result += "Номер квартиры: " + this.flatUniqueNumber +
                "\nКоличество комнат: " + this.roomsCount;
        for (int i = 0; i < this.roomsCount; i++) {
            result += "\nПлощадь " + (i + 1) + " комнаты: " + this.rooms.get(i).getRoomSquare();
        }
        result += "\nОбщая площадь квартиры: " + this.flatSquare +
                "\nКоличество жильцов: " + this.residentsCount;
        result += "\n" + GeneralConstants.separation + "\n";
        return result;
    }


}
