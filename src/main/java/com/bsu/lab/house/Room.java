package com.bsu.lab.house;

import com.bsu.lab.util.SecuredNumbersScanner;

public class Room {
    private int roomNumber;
    private double roomSquare;

   public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        String question = "Введите площадь " + (roomNumber) + "-ой комнаты(м^2)(от 1м^2 до 100м^2): ";
        this.roomSquare = SecuredNumbersScanner.EnteringInfoCheck(question);
        if (this.roomSquare <= 0 || this.roomSquare > 100)
            do {
                System.out.println("Введено неверное значение...Повторите ввод");
                this.roomSquare = SecuredNumbersScanner.EnteringInfoCheck(question);
            } while (this.roomSquare <= 0 || this.roomSquare > 100);
    }

    Room(Room room) {
        this.roomNumber = room.roomNumber;
        this.roomSquare = room.roomSquare;
    }

    public double getRoomSquare() {
        return roomSquare;
    }
}
