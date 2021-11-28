package com.bsu.lab.creator;

import com.bsu.lab.model.Room;
import com.bsu.lab.util.input.creator.inputForRoomSquare;
import org.jetbrains.annotations.NotNull;

public class RoomCreator {
    public static @NotNull Room createRoom() {
        Room room = new Room();
        double roomSquare = inputForRoomSquare.input(room.getRoomNumber()+1);
        room.setRoomSquare(roomSquare);
        return room;
    }
}
