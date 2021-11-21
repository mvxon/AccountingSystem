package com.bsu.lab.model;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
public class Room {
    private int roomNumber;
    private static int roomNumberCounter;
    @Setter
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


}
