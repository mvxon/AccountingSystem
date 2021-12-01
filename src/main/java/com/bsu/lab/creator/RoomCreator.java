package com.bsu.lab.creator;

import com.bsu.lab.model.Room;
import com.bsu.lab.util.input.creator.InputForRoomSquare;
import org.jetbrains.annotations.NotNull;

public class RoomCreator {
    public static @NotNull Room createRoom() {
        Room room = new Room();
        double roomSquare = InputForRoomSquare.input(room.getRoomNumber()+1);
        room.setRoomSquare(roomSquare);
        return room;
    }
}
