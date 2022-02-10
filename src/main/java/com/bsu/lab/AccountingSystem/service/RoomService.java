package com.bsu.lab.AccountingSystem.service;

import com.bsu.lab.AccountingSystem.model.Room;
import com.bsu.lab.AccountingSystem.util.input.service.InputForRoomSquare;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private final InputForRoomSquare inputForRoomSquare;

    @Autowired
    public RoomService(InputForRoomSquare inputForRoomSquare) {
        this.inputForRoomSquare = inputForRoomSquare;
    }

    public @NotNull Room createRoom() {
        Room room = new Room();
        room.setRoomNumber();
        double roomSquare = inputForRoomSquare.input(room.getRoomNumber());
        room.setRoomSquare(roomSquare);
        return room;
    }
}
