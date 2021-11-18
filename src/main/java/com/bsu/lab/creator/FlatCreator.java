package com.bsu.lab.creator;

import com.bsu.lab.house.Flat;
import com.bsu.lab.house.Room;
import com.bsu.lab.service.FlatService;
import com.bsu.lab.util.SecuredNumbersScanner;
import org.jetbrains.annotations.NotNull;

public class FlatCreator {
    public static @NotNull Flat createFlat() {
        Flat flat = new Flat();
        String question = "Введите количество комнат " + (flat.getFlatUniqueNumber()) +
                "-ой квартиры на этаже(от 1 до 7): ";
        flat.setRoomsCount(SecuredNumbersScanner.EnteringInfoCheck(question));
        if (flat.getRoomsCount() <= 0 || flat.getRoomsCount() > 7)
            do {
                System.out.println("Введено неверное значение...Повторите ввод");
                flat.setRoomsCount(SecuredNumbersScanner.EnteringInfoCheck(question));
            } while (flat.getRoomsCount() <= 0 || flat.getRoomsCount() > 7);

        flat.setResidentsCount((int) (Math.random() * (flat.getRoomsCount() - 1 + 1) + 1));
        System.out.println("Укажите площадь каждой комнаты: ");
        for (int i = 0; i < flat.getRoomsCount(); i++) {
            flat.addRoom(RoomCreator.createRoom()); // rooms creating
        }
        FlatService.findFlatSquare(flat);
        return flat;
    }
}
