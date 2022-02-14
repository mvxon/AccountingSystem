package com.bsu.lab.AccountingSystem.service.test;

import com.bsu.lab.AccountingSystem.model.*;
import com.bsu.lab.AccountingSystem.service.EntranceService;
import com.bsu.lab.AccountingSystem.service.FlatService;
import com.bsu.lab.AccountingSystem.service.FloorService;
import com.bsu.lab.AccountingSystem.service.HouseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = EntranceServiceTest.class)
@ComponentScan("com.bsu.lab")
class EntranceServiceTest {

    @Autowired
    EntranceService entranceService;
    @Autowired
    HouseService houseService;
    @Autowired
    FlatService flatService;
    @Autowired
    FloorService floorService;

    House houseForTest;

    @Before
    public void createHouse() {
        houseForTest = new House();
        Entrance entrance = new Entrance();
        entrance.setEntranceNumber();

        Floor floor = new Floor();
        floor.setFloorNumber();

        Flat flat = new Flat();
        flat.setFlatNumber();
        flat.setResidentsCount(5);

        Room room1 = new Room();
        room1.setRoomSquare(1);
        room1.setRoomNumber();
        flatService.addRoom(flat, room1);

        Room room2 = new Room();
        room2.setRoomSquare(2);
        room2.setRoomNumber();
        flatService.addRoom(flat, room2);

        Room room3 = new Room();
        room3.setRoomSquare(3);
        room3.setRoomNumber();
        flatService.addRoom(flat, room3);

        floorService.addFlat(floor, flat);
        floorService.addFlat(floor, new Flat(flat));

        entranceService.addFloor(entrance, floor);
        for (int i = 0; i < 4; i++) {
            entranceService.addFloor(entrance, new Floor(floor));
        }

        houseService.addEntrance(houseForTest, entrance);
        houseService.addEntrance(houseForTest, new Entrance(entrance));
    }

    @Test
    public void testGetFloorByFlatNumber() {
        Floor expectedFloor = new Floor();
        Entrance entrance = houseService.getEntranceByFlatNumber(houseForTest, 18);
        int expectedFloorNumber = 4;
        int counter = 0;
        Iterator<Floor> floorIterator = entrance.getFloors().iterator();
        while (counter < expectedFloorNumber) {
            expectedFloor = floorIterator.next();
            counter++;
        }
        Floor actualFloor = entranceService.getFloorByFlatNumber(entrance, 18);

        boolean actual = false;
        if (actualFloor.equals(expectedFloor) && actualFloor.compareTo(expectedFloor) == 0) {
            actual = true;
        }

        assertEquals(true, actual);

    }

    @Test
    public void testGetFlatsCountOnEntrance() {
        Entrance entrance = houseService.getEntranceByFlatNumber(houseForTest, 1);
        int entrancesCount = houseForTest.getEntrancesCount();
        int expected = houseService.getFlatsCount(houseForTest) / entrancesCount;
        int actual = entranceService.getFlatsCount(entrance);
        assertEquals(expected, actual);
    }

    @Test
    public void testAddFloor() {
        Entrance entrance = new Entrance();
        Floor floor = new Floor();
        floor.setFlatsCount(3);
        entranceService.addFloor(entrance, floor);
        entranceService.addFloor(entrance, floor);
        entranceService.addFloor(entrance, floor);
        int expected = 1;
        int actual = entrance.getFloorsCount();
        assertEquals(expected, actual);
    }
}
