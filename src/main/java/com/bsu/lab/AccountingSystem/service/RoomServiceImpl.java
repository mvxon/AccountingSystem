package com.bsu.lab.AccountingSystem.service;

import com.bsu.lab.AccountingSystem.domain.Room;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Override
    public @NotNull Room createRoom(double roomSquare) {
        Room room = new Room();
        room.setRoomNumber();
        room.setRoomSquare(roomSquare);
        return room;
    }
}
