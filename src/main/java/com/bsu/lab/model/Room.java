package com.bsu.lab.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue
    private int id;
    private int roomNumber;
    private static int roomNumberCounter;
    private double roomSquare;

    public void setRoomNumber() {
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
