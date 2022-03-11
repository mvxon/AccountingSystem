package com.bsu.lab.AccountingSystem.services.test;

import com.bsu.lab.AccountingSystem.entities.*;
import com.bsu.lab.AccountingSystem.services.EntranceService;
import com.bsu.lab.AccountingSystem.services.FlatService;
import com.bsu.lab.AccountingSystem.services.FloorService;
import com.bsu.lab.AccountingSystem.services.HouseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HouseServiceTest.class)
@ComponentScan("com.bsu.lab")
public class HouseServiceTest {

    @Autowired
    HouseService houseService;
    @Autowired
    EntranceService entranceService;
    @Autowired
    FloorService floorService;
    @Autowired
    FlatService flatService;

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
    public void testGetFlatsCount() {
        int expectedFlatsCount = 20;
        int actualFlatsCount = houseService.getFlatsCount(houseForTest);
        assertEquals(expectedFlatsCount, actualFlatsCount);
    }

    @Test
    public void testAddEntrance() {
        House house = new House();
        Entrance entrance = new Entrance();
        houseService.addEntrance(house, entrance);
        houseService.addEntrance(house, entrance);
        houseService.addEntrance(house, entrance);
        int expected = 1;
        int actual = house.getEntrancesCount();
        assertEquals(expected, actual);
    }

    @Test
    public void testFindTotalHouseSquare() {
        double expectedHouseSquare = 120.0;
        double actualHouseSquare = houseService.findTotalHouseSquare(houseForTest);
        assertEquals(expectedHouseSquare, actualHouseSquare);
    }

    @Test
    public void testFindTotalResidentsCount() {
        House house = new House();
        Entrance entrance = new Entrance();
        entrance.setEntranceNumber();
        Floor floor = new Floor();
        floor.setFloorNumber();

        Flat flat1 = new Flat();
        flat1.setFlatNumber();
        Flat flat2 = new Flat();
        flat2.setFlatNumber();
        Flat flat3 = new Flat();
        flat3.setFlatNumber();

        flat1.setResidentsCount(1);
        flat2.setResidentsCount(2);
        flat3.setResidentsCount(3);

        floorService.addFlat(floor, flat1);
        floorService.addFlat(floor, flat2);
        floorService.addFlat(floor, flat3);

        entranceService.addFloor(entrance, floor);
        houseService.addEntrance(house, entrance);

        int expectedResidentsCount = 6;
        int actualResidentsCount = houseService.findTotalHouseResidentsCount(house);
        assertEquals(expectedResidentsCount, actualResidentsCount);
    }

    @Test
    public void testGetEntranceByFlatNumber() {
        int flatNumber = 19;
        Entrance expectedEntrance = new Entrance();
        Iterator<Entrance> entranceIterator = houseForTest.getEntrances().iterator();
        int expectedEntranceNumber = 2;
        int counter = 0;
        while (counter < expectedEntranceNumber) {
            expectedEntrance = entranceIterator.next();
            counter++;
        }
        Entrance actualEntrance = houseService.getEntranceByFlatNumber(houseForTest, flatNumber);
        boolean actualResult = false;
        if (actualEntrance.equals(expectedEntrance) && actualEntrance.compareTo(expectedEntrance) == 0) {
            actualResult = true;
        }
        assertEquals(true, actualResult);

    }


    @Test
    public void testGetFlatByNumber() {
        int flatNumber = 4;
        Flat expectedFlat = new Flat();
        Floor expectedFloor = new Floor();
        int expectedFloorNumber = 2;
        int counter = 0;
        Iterator<Floor> floorIterator = houseForTest.getEntrances().iterator().next().getFloors().iterator();
        while (counter < expectedFloorNumber) {
            expectedFloor = floorIterator.next();
            counter++;
        }
        counter = 0;
        Iterator<Flat> flatIterator = expectedFloor.getFlats().iterator();
        while (counter < flatNumber) {
            expectedFlat = flatIterator.next();
            if (expectedFlat.getFlatNumber() == 4) {
                break;
            }
            counter++;
        }

        Flat actualFlat = houseService.getFlatByNumber(houseForTest, 4);

        boolean actualResult = false;
        if (actualFlat.equals(expectedFlat) && actualFlat.compareTo(expectedFlat) == 0) {
            actualResult = true;
        }

        assertEquals(true, actualResult);

    }
}