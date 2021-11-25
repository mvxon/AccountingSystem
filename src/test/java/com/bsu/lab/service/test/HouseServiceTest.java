package com.bsu.lab.service.test;

import com.bsu.lab.model.*;
import com.bsu.lab.creator.HouseCreatorForTest;
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

    public void testTotalHouseSquare() {
        House house = HouseCreatorForTest.createHouseForTest();
        double expectedHouseSquare = 120.0;
        double actualHouseSquare = HouseService.findTotalHouseSquare(house);
        assertEquals(expectedHouseSquare, actualHouseSquare);
    }
}