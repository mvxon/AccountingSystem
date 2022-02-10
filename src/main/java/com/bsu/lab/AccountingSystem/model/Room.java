package com.bsu.lab.AccountingSystem.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Room implements Comparable<Room> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        roomNumberCounter = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber == room.roomNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }

    @Override
    public int compareTo(@NotNull Room o) {
        if (roomSquare > o.roomSquare) return 1;
        if (roomSquare < o.roomSquare) return -1;
        return 0;
    }
}

