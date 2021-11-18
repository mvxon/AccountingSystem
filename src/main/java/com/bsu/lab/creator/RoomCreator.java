package com.bsu.lab.creator;

import com.bsu.lab.house.Room;
import com.bsu.lab.util.SecuredNumbersScanner;
import org.jetbrains.annotations.NotNull;

public class RoomCreator {
    public static @NotNull Room createRoom(){
        Room room = new Room();
        String question = "Введите площадь " + (room.getRoomNumber()+1) + "-ой комнаты(м^2)(от 1м^2 до 100м^2): ";
        room.setRoomSquare(SecuredNumbersScanner.EnteringInfoCheck(question));
        if (room.getRoomSquare() <= 0 || room.getRoomSquare() > 100)
            do {
                System.out.println("Введено неверное значение...Повторите ввод");
                room.setRoomSquare(SecuredNumbersScanner.EnteringInfoCheck(question));
            } while (room.getRoomSquare() <= 0 || room.getRoomSquare() > 100);
            return room;
    }
}
