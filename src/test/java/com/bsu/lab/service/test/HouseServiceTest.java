package com.bsu.lab.service.test;

import com.bsu.lab.model.*;
import com.bsu.lab.creator.HouseCreatorForTest;
import com.bsu.lab.service.EntranceService;
import com.bsu.lab.service.FloorService;
import com.bsu.lab.service.HouseService;
import junit.framework.TestCase;
import org.junit.After;

import java.util.Iterator;

public class HouseServiceTest extends TestCase {
    private static final House houseForTest = HouseCreatorForTest.createHouseForTest();

    public void testGetFlatsCount() {
        int expectedFlatsCount = 20;
        int actualFlatsCount = HouseService.getFlatsCount(houseForTest);
        assertEquals(expectedFlatsCount, actualFlatsCount);
    }

    public void testAddEntrance() {
        House house = new House();
        Entrance entrance = new Entrance();
        HouseService.addEntrance(house, entrance);
        HouseService.addEntrance(house, entrance);
        HouseService.addEntrance(house, entrance);
        int expected = 1;
        int actual = house.getEntrancesCount();
        assertEquals(expected, actual);
    }

    public void testFindTotalHouseSquare() {
        double expectedHouseSquare = 120.0;
        double actualHouseSquare = HouseService.findTotalHouseSquare(houseForTest);
        assertEquals(expectedHouseSquare, actualHouseSquare);
    }

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

        FloorService.addFlat(floor, flat1);
        FloorService.addFlat(floor, flat2);
        FloorService.addFlat(floor, flat3);

        EntranceService.addFloor(entrance, floor);
        HouseService.addEntrance(house, entrance);

        int expectedResidentsCount = 6;
        int actualResidentsCount = HouseService.findTotalHouseResidentsCount(house);
        assertEquals(expectedResidentsCount, actualResidentsCount);
    }

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
        Entrance actualEntrance = HouseService.getEntranceByFlatNumber(houseForTest, flatNumber);
        boolean actualResult = false;
        if (actualEntrance.equals(expectedEntrance) && actualEntrance.compareTo(expectedEntrance) == 0) {
            actualResult = true;
        }
        assertEquals(true, actualResult);

    }


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

        Flat actualFlat = HouseService.getFlatByNumber(houseForTest, 4);

        boolean actualResult = false;
        if (actualFlat.equals(expectedFlat) && actualFlat.compareTo(expectedFlat) == 0) {
            actualResult = true;
        }

        assertEquals(true, actualResult);

    }
}