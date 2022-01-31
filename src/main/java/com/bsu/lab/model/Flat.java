package com.bsu.lab.model;


import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "flats")
public class Flat implements Comparable<Flat> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int residentsCount;
    private int roomsCount = 0;
    private int flatNumber;
    private static int flatNumberCounter;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "flat_id")
    private Set<Room> rooms = new LinkedHashSet<>();

    public Flat() {
        Room.nullifyRoomNumberCounter();
    }

    public void setFlatNumber() {
        this.flatNumber = flatNumberCounter;
        flatNumberCounter++;
    }

    public Flat(@NotNull Flat flat) { // copy constructor for Flat
        this.flatNumber = flatNumberCounter;
        flatNumberCounter++;
        Room.nullifyRoomNumberCounter();
        this.roomsCount = flat.roomsCount;
        this.residentsCount = (int) (Math.random() * (this.roomsCount - 1 + 1) + 1);
        for (Room room : flat.rooms) {
            this.rooms.add(new Room(room));
        }
    }

    public static void nullifyFlatNumberCounter() {
        flatNumberCounter = 1;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return flatNumber == flat.flatNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(flatNumber);
    }

    @Override
    public int compareTo(@NotNull Flat o) {
        if (roomsCount > o.roomsCount) return 1;
        if (roomsCount < o.roomsCount) return -1;
        return 0;
    }
}
