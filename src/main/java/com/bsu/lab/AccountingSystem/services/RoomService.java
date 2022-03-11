package com.bsu.lab.AccountingSystem.services;

import com.bsu.lab.AccountingSystem.entities.Room;
import org.jetbrains.annotations.NotNull;

public interface RoomService {
    @NotNull Room createRoom(double roomSquare);
}
