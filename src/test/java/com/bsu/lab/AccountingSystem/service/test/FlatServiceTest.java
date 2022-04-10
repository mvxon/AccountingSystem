package com.bsu.lab.AccountingSystem.service.test;

import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.domain.Room;
import com.bsu.lab.AccountingSystem.service.FlatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlatServiceTest.class)
@ComponentScan("com.bsu.lab")
public class FlatServiceTest {

    @Autowired
    FlatService flatService;


    @Test
    public void testFindFlatSquare() {
        Flat flat = new Flat();

        Room firstRoom = new Room();
        firstRoom.setRoomSquare(5);
        firstRoom.setRoomNumber();

        Room secondRoom = new Room();
        secondRoom.setRoomSquare(25);
        secondRoom.setRoomNumber();

        flatService.addRoom(flat, firstRoom);
        flatService.addRoom(flat, secondRoom);


        double expectedFlatSquare = 30;
        double actualFlatSquare = flatService.findFlatSquare(flat);
        assertEquals(expectedFlatSquare, actualFlatSquare);
    }

    @Test
    public void testAddRoom() {
        Flat flat = new Flat();
        Room room = new Room();
        flatService.addRoom(flat, room);
        int expectedRoomsCount = 1;
        int actualRoomsCount = flat.getRoomsCount();
        assertEquals(expectedRoomsCount, actualRoomsCount);
    }
}
