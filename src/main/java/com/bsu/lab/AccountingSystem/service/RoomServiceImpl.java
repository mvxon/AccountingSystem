package com.bsu.lab.AccountingSystem.service;

import com.bsu.lab.AccountingSystem.domain.Room;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Override
    public @NotNull Room createRoom(double roomSquare) {
        Room room = new Room();
        room.setRoomSquare(roomSquare);
        return room;
    }

    @Override
    public Room copyRoom(Room room) {
        Room copy = new Room();
        copy.setRoomSquare(room.getRoomSquare());
        copy.setRoomNumber(room.getRoomNumber());
        return copy;
    }
}
