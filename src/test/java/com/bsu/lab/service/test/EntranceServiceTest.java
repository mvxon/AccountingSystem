package com.bsu.lab.service.test;

import com.bsu.lab.creator.HouseCreatorForTest;
import com.bsu.lab.model.Entrance;
import com.bsu.lab.model.Floor;
import com.bsu.lab.model.House;
import com.bsu.lab.service.EntranceService;
import com.bsu.lab.service.HouseService;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Iterator;

public class EntranceServiceTest extends TestCase {

    private static final House house = HouseCreatorForTest.createHouseForTest();

    @Test
    public void testGetFloorByFlatNumber() {
        Floor expectedFloor = new Floor();
        Entrance entrance = HouseService.getEntranceByFlatNumber(house, 18);
        int expectedFloorNumber = 4;
        int counter = 0;
        Iterator<Floor> floorIterator = entrance.getFloors().iterator();
        while (counter < expectedFloorNumber) {
            expectedFloor = floorIterator.next();
            counter++;
        }
        Floor actualFloor = EntranceService.getFloorByFlatNumber(entrance, 18);

        boolean actual = false;
        if (actualFloor.equals(expectedFloor) && actualFloor.compareTo(expectedFloor) == 0) {
            actual = true;
        }

        assertEquals(true, actual);

    }

    @Test
    public void testGetFlatsCountOnEntrance() {
        Entrance entrance = HouseService.getEntranceByFlatNumber(house, 1);
        int entrancesCount = house.getEntrancesCount();
        int expected = HouseService.getFlatsCount(house) / entrancesCount;
        int actual = EntranceService.getFlatsCount(entrance);
        assertEquals(expected, actual);
    }

    @Test
    public void testAddFloor() {
        Entrance entrance = new Entrance();
        Floor floor = new Floor();
        floor.setFlatsCount(3);
        EntranceService.addFloor(entrance, floor);
        EntranceService.addFloor(entrance, floor);
        EntranceService.addFloor(entrance, floor);
        int expected = 1;
        int actual = entrance.getFloorsCount();
        assertEquals(expected, actual);
    }
}