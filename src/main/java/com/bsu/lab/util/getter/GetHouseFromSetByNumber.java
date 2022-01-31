package com.bsu.lab.util.getter;

import com.bsu.lab.model.House;
import org.jetbrains.annotations.NotNull;


import java.util.Set;

public class GetHouseFromSetByNumber {
    public static @NotNull House get(@NotNull Set<House> setOfHouses, int houseNumber) {
        if (!setOfHouses.isEmpty()) {
            for (House house : setOfHouses) {
                if (house.getHouseNumber() == houseNumber) {
                    return house;
                }
            }
        }
        return new House();
    }
}
