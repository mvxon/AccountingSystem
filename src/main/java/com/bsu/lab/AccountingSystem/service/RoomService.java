package com.bsu.lab.AccountingSystem.service;

import com.bsu.lab.AccountingSystem.model.Room;
import com.bsu.lab.AccountingSystem.util.input.service.InputForRoomSquare;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    public @NotNull Room createRoom(double roomSquare) {
        Room room = new Room();
        room.setRoomNumber();
        room.setRoomSquare(roomSquare);
        return room;
    }
}
