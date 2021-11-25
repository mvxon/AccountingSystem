package com.bsu.lab.creator;

import com.bsu.lab.model.Flat;
import com.bsu.lab.service.FlatService;
import com.bsu.lab.util.SecuredNumbersScanner;
import com.bsu.lab.util.validation.validationForRoomsCount;
import org.jetbrains.annotations.NotNull;

public class FlatCreator {
    public static @NotNull Flat createFlat() {
        Flat flat = new Flat();
        int roomsCount = validationForRoomsCount.validate(flat.getFlatNumber());
        flat.setResidentsCount((int) (Math.random() * (flat.getRoomsCount() - 1 + 1) + 1));
        System.out.println("Укажите площадь каждой комнаты: ");
        for (int i = 0; i < roomsCount; i++) {
            FlatService.addRoom(flat, RoomCreator.createRoom()); // rooms creating
        }
        return flat;
    }
}
