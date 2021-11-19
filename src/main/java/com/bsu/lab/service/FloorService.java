package com.bsu.lab.service;

import com.bsu.lab.house.Flat;
import com.bsu.lab.house.Floor;
import org.jetbrains.annotations.NotNull;

public class FloorService {
    public static void addFlat(@NotNull Floor floor, @NotNull Flat flat) {
        floor.getFlats().add(flat);
        floor.setFlatsPerFloor(floor.getFlatsPerFloor()+1);
    }
}
