/*
package com.bsu.lab.AccountingSystem.service.test;

import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.domain.Floor;
import com.bsu.lab.AccountingSystem.service.FloorService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FloorServiceTest.class)
@ComponentScan("com.bsu.lab")
public class FloorServiceTest {

    @Autowired
    FloorService floorService;

    @Test
    public void testAddFlat() {
        Floor floor = new Floor();
        Flat flat = new Flat();
        floorService.addFlat(floor, flat);
        floorService.addFlat(floor, flat);
        floorService.addFlat(floor, flat);
        int expectedFlatsCount = 1;
        int actualFlatsCount = floor.getFlatsCount();
        assertEquals(expectedFlatsCount, actualFlatsCount);

    }
}
*/
