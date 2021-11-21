package com.bsu.lab.creator;

import com.bsu.lab.model.Room;
import com.bsu.lab.util.SecuredNumbersScanner;
import org.jetbrains.annotations.NotNull;

public class RoomCreator {
    public static @NotNull Room createRoom() {
        Room room = new Room();
        String question = "Введите площадь " + (room.getRoomNumber() + 1) + "-ой комнаты(м^2)(от 1м^2 до 100м^2): ";
        double roomSquare = (SecuredNumbersScanner.EnteringInfoCheck(question));
        if (roomSquare <= 0 || roomSquare > 100) {
            do {
                System.out.println("Введено неверное значение...Повторите ввод");
                roomSquare = (SecuredNumbersScanner.EnteringInfoCheck(question));
            } while (roomSquare <= 0 || roomSquare > 100);
        }
        room.setRoomSquare(roomSquare);
        return room;
    }
}
