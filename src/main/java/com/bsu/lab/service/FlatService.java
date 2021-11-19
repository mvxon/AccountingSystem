package com.bsu.lab.service;


import com.bsu.lab.house.Flat;
import com.bsu.lab.house.House;
import com.bsu.lab.house.Room;
import com.bsu.lab.util.constants.GeneralConstants;
import org.jetbrains.annotations.NotNull;

public class FlatService {
    public static void findFlatSquare(@NotNull Flat flat) {
        double result = 0;
        for (int i = 0; i < flat.getRoomsCount(); i++) {
            result += flat.getRooms().get(i).getRoomSquare();
        }
        flat.setFlatSquare(result);
    }

    public static @NotNull String flatInfoToString(@NotNull House house, @NotNull Flat flat) {
        String result = "";
        result += "\n" + GeneralConstants.SEPARATION + "\n";
        result += "Номер квартиры: " + flat.getFlatUniqueNumber() +
                "\nНомер дома: " + house.getHouseNumber() +
                "\nНомер подъезда: "
                + (HouseService.getEntranceByFlatNumber(house, flat.getFlatUniqueNumber()).getEntranceNumber() + 1) +
                "\nКоличество комнат: " + flat.getRoomsCount();
        for (int i = 0; i < flat.getRoomsCount(); i++) {
            result += "\nПлощадь " + (i + 1) + " комнаты: " + flat.getRooms().get(i).getRoomSquare();
        }
        result += "\nОбщая площадь квартиры: " + flat.getFlatSquare() + "\nКоличество жильцов: "
                + flat.getResidentsCount();
        result += "\n" + GeneralConstants.SEPARATION + "\n";
        return result;
    }

    public static void addRoom(@NotNull Flat flat, @NotNull Room room) {
        flat.getRooms().add(room);
        flat.setRoomsCount(flat.getRoomsCount() + 1);
    }


}
