package com.bsu.lab.house;

import com.bsu.lab.service.FlatService;
import com.bsu.lab.util.SecuredNumbersScanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Flat {
    private double flatSquare;
    private int residentsCount;
    private int roomsCount;
    private int flatUniqueNumber;
    private static int flatNumberCounter = 0;
    private List<Room> rooms = new ArrayList<>();

    public Flat(int flatNumber) {
        flatNumberCounter = flatNumber;
        this.flatUniqueNumber = flatNumberCounter;
        flatNumberCounter++;
        String question = "Введите количество комнат " + (flatUniqueNumber) +
                "-ой квартиры на этаже(от 1 до 7): ";
        this.roomsCount = SecuredNumbersScanner.EnteringInfoCheck(question);
        if (this.roomsCount <= 0 || this.roomsCount > 7)
            do {
                System.out.println("Введено неверное значение...Повторите ввод");
                this.roomsCount = SecuredNumbersScanner.EnteringInfoCheck(question);
            } while (this.roomsCount <= 0 || this.roomsCount > 7);

        this.residentsCount = (int) (Math.random() * (this.roomsCount - 1 + 1) + 1);
        System.out.println("Укажите площадь каждой комнаты: ");
        for (int i = 0; i < roomsCount; i++) {
            this.rooms.add(new Room(i + 1)); // rooms creating

        }
        FlatService.findFlatSquare(this);
    }

    Flat(Flat flat) { // copy constructor for Flat
        this.flatUniqueNumber = flatNumberCounter;
        flatNumberCounter++;
        this.roomsCount = flat.roomsCount;
        this.flatSquare = flat.flatSquare;
        this.residentsCount = (int) (Math.random() * (this.roomsCount - 1 + 1) + 1);
        for (int i = 0; i < this.roomsCount; i++) {
            this.rooms.add(new Room(flat.rooms.get(i)));
        }
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

    public double getFlatSquare() {
        return flatSquare;
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
        result += "\n--------------------------------------------------------------------------------\n";
        result += "Номер квартиры: " + this.flatUniqueNumber +
                "\nКоличество комнат: " + this.roomsCount;
        for (int i = 0; i < this.roomsCount; i++) {
            result += "\nПлощадь " + (i + 1) + " комнаты: " + this.rooms.get(i).getRoomSquare();
        }
        result += "\nОбщая площадь квартиры: " + this.flatSquare +
                "\nКоличество жильцов: " + this.residentsCount;
        result += "\n--------------------------------------------------------------------------------\n";
        return result;
    }


}
