package com.bsu.lab.service.test;

import com.bsu.lab.model.Entrance;
import com.bsu.lab.model.Floor;
import com.bsu.lab.service.EntranceService;
import junit.framework.TestCase;
import org.junit.Test;


public class EntranceServiceTest extends TestCase {

    @Test
    public void testAddFloor() {
        Entrance entrance = new Entrance();
        Floor floor = new Floor();
        floor.setFlatsCount(3);
        EntranceService.addFloor(entrance, floor);
        int expected = 1;
        int actual = entrance.getFloorsCount();
        assertEquals(expected, actual);
    }
}
