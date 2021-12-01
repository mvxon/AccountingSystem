package com.bsu.lab.creator;

import com.bsu.lab.model.Flat;
import com.bsu.lab.service.FlatService;
import com.bsu.lab.util.input.creator.InputForRoomsCount;
import org.jetbrains.annotations.NotNull;

public class FlatCreator {
    public static @NotNull Flat createFlat() {
        Flat flat = new Flat();
        int roomsCount = InputForRoomsCount.input(flat.getFlatNumber());
        flat.setResidentsCount((int) (Math.random() * (flat.getRoomsCount() - 1 + 1) + 1));
        System.out.println("Укажите площадь каждой комнаты: ");
        while (flat.getRoomsCount() < roomsCount) {
            FlatService.addRoom(flat, RoomCreator.createRoom()); // rooms creating
        }
        return flat;
    }
}
