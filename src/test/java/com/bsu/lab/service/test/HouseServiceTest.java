package com.bsu.lab.service.test;

import com.bsu.lab.model.*;
import com.bsu.lab.creator.HouseCreatorForTest;
import com.bsu.lab.service.EntranceService;
import com.bsu.lab.service.FloorService;
import com.bsu.lab.service.HouseService;
import junit.framework.TestCase;

public class HouseServiceTest extends TestCase {


    public void testGetFlatsCount() {
        House house = HouseCreatorForTest.createHouseForTest();
        int expectedFlatsCount = 20;
        int actualFlatsCount = HouseService.getFlatsCount(house);
        assertEquals(expectedFlatsCount, actualFlatsCount);
    }

    public void testAddEntrance() {
        House house = new House();
        Entrance entrance = new Entrance();
        HouseService.addEntrance(house, entrance);
        int expected = 1;
        int actual = house.getEntrancesCount();
        assertEquals(expected, actual);
    }

    public void testFindTotalHouseSquare() {
        House house = HouseCreatorForTest.createHouseForTest();
        double expectedHouseSquare = 120.0;
        double actualHouseSquare = HouseService.findTotalHouseSquare(house);
        assertEquals(expectedHouseSquare, actualHouseSquare);
    }

    public void testFindTotalResidentsCount() {
        House house = new House();
        Entrance entrance = new Entrance();
        Floor floor = new Floor();

        Flat flat1 = new Flat();
        Flat flat2 = new Flat();
        Flat flat3 = new Flat();

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
}