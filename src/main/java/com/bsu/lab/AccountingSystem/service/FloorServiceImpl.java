package com.bsu.lab.AccountingSystem.service;


import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.domain.Floor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        int flatsCount = squareOfRoomsOfFlats.size();
        for (int i = 0; i < flatsCount; i++) {
            Flat flat = flatService.createFlat(squareOfRoomsOfFlats.get(i));
            flat.setFlatNumber(i + 1); // temp flat number
            addFlat(floor, flat);
        }
        return floor;
    }

    @Override
    public Floor copyFloor(Floor floor) {
        Floor copy = new Floor();
        copy.setFlatsCount(floor.getFlatsCount());
        int flatNumberCounter = 0;
        for (Flat flat : floor.getFlats()) {
            Flat flatCopy = flatService.copyFlat(flat);
            flatCopy.setFlatNumber(++flatNumberCounter);
            addFlat(copy, flatCopy);
        }
        return copy;
    }

    @Override
    public void addFlat(@NotNull Floor floor, @NotNull Flat flat) {
        if (floor.getFlats().add(flat)) {
            floor.setFlatsCount(floor.getFlatsCount() + 1);
        }
    }


}
