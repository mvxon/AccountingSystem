package com.bsu.lab.AccountingSystem.service;


import com.bsu.lab.AccountingSystem.dao.EntranceRepository;
import com.bsu.lab.AccountingSystem.domain.Entrance;
import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.domain.Floor;
import com.bsu.lab.AccountingSystem.domain.Room;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class EntranceServiceImpl implements EntranceService {
    private final FloorService floorService;
    private final EntranceRepository entranceRepository;

    @Autowired
    public EntranceServiceImpl(@Lazy FloorService floorService,
                               EntranceRepository entranceRepository) {
        this.floorService = floorService;
        this.entranceRepository = entranceRepository;
    }

    @Override
    public @NonNull Entrance createEntrance(int floorsCount, List<List<Double>> squareOfRoomsOfFlats) {
        Entrance entrance = new Entrance();
        int floorNumberCounter = 0;
        Set<Floor> floors = new HashSet<>();
        entrance.setFloors(floors);
        while (entrance.getFloorsCount() < floorsCount) {
            Floor floor;
            if (entrance.getFloorsCount() == 0) {
                floor = floorService.createFloor(squareOfRoomsOfFlats);
            } else {
                floor = floorService.copyFloor(entrance.getFloors().iterator().next());
            }
            floor.setFloorNumber(++floorNumberCounter);
            addFloor(entrance, floor);
        }
        return entrance;
    }

    @Override
    public @NotNull Entrance copyEntrance(Entrance entrance) {
        Entrance copy = new Entrance();
        int floorNumberCounter = 0;
        Set<Floor> floors = new HashSet<>();
        copy.setFloors(floors);
        for (Floor floor : entrance.getFloors()) {
            Floor floorCopy = floorService.copyFloor(floor);
            floorCopy.setFloorNumber(++floorNumberCounter);
            addFloor(copy, floorCopy);
        }
        return copy;
    }

    @Override
    public void addFloor(@NotNull Entrance entrance, @NotNull Floor floor) {
        if (entrance.getFloors().add(floor)) {
            entrance.setFloorsCount(entrance.getFloorsCount() + 1);
        }
    }

    @Override
    public Floor getFloorByFlatNumber(@NotNull Entrance entrance, int flatNumber) {
        for (Floor floor : entrance.getFloors()) {
            for (Flat flat : floor.getFlats()) {
                if (flat.getFlatNumber() == flatNumber) return floor;
            }
        }
        return new Floor();
    }

    @Override
    public int getFlatsCount(@NotNull Entrance entrance) {
        int flatsPerFloor = entrance.getFloors().iterator().next().getFlatsCount();
        int floorsCount = entrance.getFloorsCount();
        return flatsPerFloor * floorsCount;
    }

    @Override
    public Entrance getEntranceByFloor(Floor floor) {
        return entranceRepository.getByFloorsContains(floor);
    }
}
