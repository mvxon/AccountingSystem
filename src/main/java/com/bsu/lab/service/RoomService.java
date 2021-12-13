package com.bsu.lab.service;

import com.bsu.lab.model.Room;
import com.bsu.lab.util.input.service.inputForRoomSquare;
import org.jetbrains.annotations.NotNull;

public class RoomService {
    private static RoomService roomService;

    public static RoomService getInstance() {
        if (roomService == null) {
            roomService = new RoomService();
        }
        return roomService;
    }

    public static @NotNull Room createRoom() {
        Room room = new Room();
        room.setRoomNumber();
        double roomSquare = inputForRoomSquare.input(room.getRoomNumber() + 1);
        room.setRoomSquare(roomSquare);
        return room;
    }
}
