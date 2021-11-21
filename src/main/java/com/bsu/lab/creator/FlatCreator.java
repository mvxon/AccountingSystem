package com.bsu.lab.creator;

import com.bsu.lab.model.Flat;
import com.bsu.lab.service.FlatService;
import com.bsu.lab.util.SecuredNumbersScanner;
import org.jetbrains.annotations.NotNull;

public class FlatCreator {
    public static @NotNull Flat createFlat() {
        Flat flat = new Flat();
        String question = "Введите количество комнат " + (flat.getFlatNumber()) +
                "-ой квартиры на этаже(от 1 до 7): ";
        int roomsCount = (SecuredNumbersScanner.EnteringInfoCheck(question));
        if (roomsCount <= 0 || roomsCount > 7) {
            do {
                System.out.println("Введено неверное значение...Повторите ввод");
                roomsCount = (SecuredNumbersScanner.EnteringInfoCheck(question));
            } while (roomsCount <= 0 || roomsCount > 7);
        }

        flat.setResidentsCount((int) (Math.random() * (flat.getRoomsCount() - 1 + 1) + 1));
        System.out.println("Укажите площадь каждой комнаты: ");
        for (int i = 0; i < roomsCount; i++) {
            FlatService.addRoom(flat, RoomCreator.createRoom()); // rooms creating
        }
        return flat;
    }
}
