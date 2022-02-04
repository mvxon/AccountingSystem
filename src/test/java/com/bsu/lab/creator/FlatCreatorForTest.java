package com.bsu.lab.creator;

import com.bsu.lab.model.Flat;
import com.bsu.lab.model.Room;
import com.bsu.lab.service.FlatService;
import org.jetbrains.annotations.NotNull;

public class FlatCreatorForTest {
    public static @NotNull Flat createFlat() {
        Flat flat = new Flat();

        Room room1 = new Room();
        room1.setRoomNumber();
        room1.setRoomSquare(10);

        Room room2 = new Room();
        room2.setRoomNumber();
        room2.setRoomSquare(20);

        FlatService.addRoom(flat, room1);
        FlatService.addRoom(flat, room2);

        return flat;
    }
}
