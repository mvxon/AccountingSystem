package com.bsu.lab.AccountingSystem.service;


import com.bsu.lab.AccountingSystem.dao.FloorRepository;
import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.domain.Floor;
import com.bsu.lab.AccountingSystem.domain.Room;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FloorServiceImpl implements FloorService {
    private final FlatService flatService;
    private final FloorRepository floorRepository;

    @Autowired
    public FloorServiceImpl(FlatService flatService,
                            FloorRepository floorRepository) {
        this.flatService = flatService;
        this.floorRepository = floorRepository;
    }

    @Override
    public @NotNull Floor createFloor(@NotNull List<ArrayList<Double>> squareOfRoomsOfFlats) {
        Floor floor = new Floor();
        Set<Flat> flats = new HashSet<>();
        floor.setFlats(flats);
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
        Set<Flat> flats = new HashSet<>();
        copy.setFlats(flats);
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

    @Override
    public Floor getFloorByFlat(Flat flat) {
        return floorRepository.getByFlatsContains(flat);
    }


}
