package com.bsu.lab.creator;

import com.bsu.lab.model.Room;
import com.bsu.lab.util.SecuredNumbersScanner;
import com.bsu.lab.util.validation.validationForRoomSquare;
import org.jetbrains.annotations.NotNull;

public class RoomCreator {
    public static @NotNull Room createRoom() {
        Room room = new Room();
        double roomSquare = validationForRoomSquare.validate(room.getRoomNumber()+1);
        room.setRoomSquare(roomSquare);
        return room;
    }
}
