package com.bsu.lab.service;


import com.bsu.lab.house.Flat;
import com.bsu.lab.house.House;

public class FlatService {
    public static void findFlatSquare(Flat flat) {
        double result = 0;
        for (int i = 0; i < flat.getRoomsCount(); i++) {
            result += flat.getRooms().get(i).getRoomSquare();
        }
        flat.setFlatSquare(result);
    }

    public static String flatInfoToString(House house, Flat flat) {
        String result = "";
        result += "\n--------------------------------------------------------------------------------\n";
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
        result += "\n--------------------------------------------------------------------------------\n";
        return result;
    }


}
