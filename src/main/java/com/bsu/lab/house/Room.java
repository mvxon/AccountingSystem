package com.bsu.lab.house;

import com.bsu.lab.util.SecuredNumbersScanner;
import org.jetbrains.annotations.NotNull;

public class Room {
    private int roomNumber;
    private static int roomNumberCounter;
    private double roomSquare;

    public Room() {
        this.roomNumber = roomNumberCounter;
        roomNumberCounter++;
    }

    Room(@NotNull Room room) {
        this.roomNumber = room.roomNumber;
        this.roomSquare = room.roomSquare;
    }

    public static void nullifyRoomNumberCounter() {
        roomNumberCounter = 0;
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }

    public double getRoomSquare() {
        return roomSquare;
    }

    public void setRoomSquare(double roomSquare) {
        this.roomSquare = roomSquare;
    }
}
