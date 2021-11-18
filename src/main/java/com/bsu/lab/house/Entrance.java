package com.bsu.lab.house;

import com.bsu.lab.util.SecuredNumbersScanner;
import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;
import java.util.List;

public class Entrance {
    private static int entranceNumberCounter;
    public int entranceNumber;
    private int floorsCount;
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
        for (int i = 0; i < floorsCount; i++) {
            this.floors.add(new Floor(entrance.floors.get(0)));
        }
    }
    public void setFloorsCount(int floorsCount){
        this.floorsCount = floorsCount;
    }
    public void addFloor(Floor floor){
        this.floors.add(floor);
    }
    public static void NullifyEntranceNumberCounter(){
        entranceNumberCounter = 0;
    }

        public int getFloorsCount () {
            return floorsCount;
        }

        public int getEntranceNumber () {
            return entranceNumber;
        }

        public List<Floor> getFloors () {
            return floors;
        }
    }

