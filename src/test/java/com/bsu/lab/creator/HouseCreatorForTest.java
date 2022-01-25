package com.bsu.lab.creator;

import com.bsu.lab.model.*;
import com.bsu.lab.service.EntranceService;
import com.bsu.lab.service.FlatService;
import com.bsu.lab.service.FloorService;
import com.bsu.lab.service.HouseService;
import org.jetbrains.annotations.NotNull;

public class HouseCreatorForTest {
    public static @NotNull House createHouseForTest() {
        House house = new House();

        Entrance entrance = new Entrance();
        entrance.setEntranceNumber();

        Floor floor = new Floor();
        floor.setFloorNumber();

        Flat flat = new Flat();
        flat.setFlatNumber();
        flat.setResidentsCount(5);

        Room room1 = new Room();
        room1.setRoomSquare(1);
        FlatService.addRoom(flat, room1);

        Room room2 = new Room();
        room2.setRoomSquare(2);
        FlatService.addRoom(flat, room2);

        Room room3 = new Room();
        room3.setRoomSquare(3);
        FlatService.addRoom(flat, room3);

        FloorService.addFlat(floor, flat);
        FloorService.addFlat(floor, new Flat(flat));

        EntranceService.addFloor(entrance, floor);
        for (int i = 0; i < 4; i++) {
            EntranceService.addFloor(entrance, new Floor(floor));
        }

        HouseService.addEntrance(house, entrance);
        HouseService.addEntrance(house, new Entrance(entrance));
        return house;
    }
}
