package com.bsu.lab.AccountingSystem.service;

import com.bsu.lab.AccountingSystem.domain.Room;
import org.jetbrains.annotations.NotNull;

public interface RoomService {

    @NotNull Room createRoom(double roomSquare);
}
