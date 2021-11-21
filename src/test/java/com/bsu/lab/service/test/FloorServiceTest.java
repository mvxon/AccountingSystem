package com.bsu.lab.service.test;

import com.bsu.lab.model.Flat;
import com.bsu.lab.model.Floor;
import com.bsu.lab.service.FloorService;
import junit.framework.TestCase;

public class FloorServiceTest extends TestCase {

    public void testAddFlat() {
        Floor floor = new Floor();
        Flat flat = new Flat();
        FloorService.addFlat(floor, flat);
        int expectedFlatsCount = 1;
        int actualFlatsCount = floor.getFlatsPerFloor();
        assertEquals(expectedFlatsCount, actualFlatsCount);

    }
}