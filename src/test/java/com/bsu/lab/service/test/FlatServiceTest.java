package com.bsu.lab.service.test;

import com.bsu.lab.model.Flat;
import com.bsu.lab.model.Room;
import com.bsu.lab.creator.FlatCreatorForTest;
import com.bsu.lab.service.FlatService;
import junit.framework.TestCase;

public class FlatServiceTest extends TestCase {

    public void testFindFlatSquare() {
        Flat flat = FlatCreatorForTest.createFlat();
        double expectedFlatSquare = 30;
        double actualFlatSquare = FlatService.findFlatSquare(flat);
        assertEquals(expectedFlatSquare, actualFlatSquare);
    }

    public void testAddRoom() {
        Flat flat = new Flat();
        Room room = new Room();
        FlatService.addRoom(flat, room);
        int expectedRoomsCount = 1;
        int actualRoomsCount = flat.getRoomsCount();
        assertEquals(expectedRoomsCount, actualRoomsCount);
    }
}