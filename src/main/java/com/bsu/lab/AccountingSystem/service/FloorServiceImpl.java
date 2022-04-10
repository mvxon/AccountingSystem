package com.bsu.lab.AccountingSystem.service;


import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.domain.Floor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FloorServiceImpl implements FloorService {
    private final FlatService flatService;

    @Autowired
    public FloorServiceImpl(FlatService flatService) {
        this.flatService = flatService;
    }

    @Override
    public @NotNull Floor createFloor(@NotNull List<ArrayList<Double>> squareOfRoomsOfFlats) {
        Floor floor = new Floor();
        floor.setFloorNumber();
        int flatsCount = squareOfRoomsOfFlats.size();
        for (int i = 0; i < flatsCount; i++) {
            this.addFlat(floor, flatService.createFlat(squareOfRoomsOfFlats.get(i))); // flats creating
        }
        return floor;
    }

    @Override
    public void addFlat(@NotNull Floor floor, @NotNull Flat flat) {
        if (floor.getFlats().add(flat)) {
            floor.setFlatsCount(floor.getFlatsCount() + 1);
        }
    }


}
