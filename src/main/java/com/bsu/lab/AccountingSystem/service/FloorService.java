package com.bsu.lab.AccountingSystem.service;



import com.bsu.lab.AccountingSystem.model.Flat;
import com.bsu.lab.AccountingSystem.model.Floor;
import com.bsu.lab.AccountingSystem.util.input.service.InputForFlatsCount;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FloorService {
    private final FlatService flatService;

    @Autowired
    public FloorService(FlatService flatService) {
        this.flatService = flatService;
    }

    public @NotNull Floor createFloor(@NotNull List<ArrayList<Double>> squareOfRoomsOfFlats) {
        Floor floor = new Floor();
        floor.setFloorNumber();
        int flatsCount = squareOfRoomsOfFlats.size();
        for (int i = 0; i < flatsCount; i++) {
            this.addFlat(floor, flatService.createFlat( squareOfRoomsOfFlats.get(i))); // flats creating
        }
        return floor;
    }

    public void addFlat(@NotNull Floor floor, @NotNull Flat flat) {
        if (floor.getFlats().add(flat)) {
            floor.setFlatsCount(floor.getFlatsCount() + 1);
        }
    }


}
