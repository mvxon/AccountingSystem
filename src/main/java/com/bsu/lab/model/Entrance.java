package com.bsu.lab.model;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;
import java.util.List;

@Getter
public class Entrance {
    private static int entranceNumberCounter;
    public int entranceNumber;
    @Setter
    private int floorsCount = 0;
    private final List<Floor> floors = new ArrayList<>();


    public Entrance() {
        this.entranceNumber = entranceNumberCounter;
        entranceNumberCounter++;
        Floor.NullifyFloorNumberCounter();
    }

    // copy constructor
    public Entrance(@NotNull Entrance entrance) {
        this.entranceNumber = entranceNumberCounter;
        entranceNumberCounter++;
        Floor.NullifyFloorNumberCounter();
        this.floorsCount = entrance.floorsCount;
        for (int i = 0; i < this.floorsCount; i++) {
            this.floors.add(new Floor(entrance.floors.get(0)));
        }
    }


    public static void NullifyEntranceNumberCounter() {
        entranceNumberCounter = 0;
    }

}


