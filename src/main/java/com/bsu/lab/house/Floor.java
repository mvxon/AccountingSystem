package com.bsu.lab.house;

import com.bsu.lab.util.SecuredNumbersScanner;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private  int floorNumber;
    private static int floorNumberCounter;
    private final List<Flat> flats = new ArrayList<>();
    private static int flatsPerFloor;

   public Floor() {
        this.floorNumber = floorNumberCounter;
        floorNumberCounter++;

    }
 // copy constructor
    public Floor(Floor floor) {
        this.floorNumber = floorNumberCounter;
        floorNumberCounter++;
        for (int i = 0; i < flatsPerFloor; i++) {
            this.flats.add(new Flat(floor.flats.get(i)));
        }
    }
    public static void NullifyFloorNumberCounter(){
        floorNumberCounter = 0;
    }

    public static void setFlatsPerFloor(int flatsPerFloor) {
        Floor.flatsPerFloor = flatsPerFloor;
    }
public void addFlat(Flat flat){
       this.flats.add(flat);
}
    public int getFloorNumber() {
        return this.floorNumber;
    }

    public int getFlatsPerFloor() {
        return flatsPerFloor;
    }

    public List<Flat> getFlats() {
        return flats;
    }
}
