package com.bsu.lab.service;


import com.bsu.lab.model.Flat;
import com.bsu.lab.model.House;
import com.bsu.lab.model.Room;
import com.bsu.lab.constant.GeneralConstants;
import com.bsu.lab.util.input.service.inputForRoomsCount;
import org.jetbrains.annotations.NotNull;

public class FlatService {
    private static FlatService flatService;

    private static FlatService getInstance() {
        if (flatService == null) {
            flatService = new FlatService();
        }
        return flatService;
    }

    public static double findFlatSquare(@NotNull Flat flat) {
        double result = 0;
        for (int i = 0; i < flat.getRoomsCount(); i++) {
            result += flat.getRooms().get(i).getRoomSquare();
        }
        return result;
    }

    public static @NotNull Flat createFlat() {
        Flat flat = new Flat();
        flat.setFlatNumber();
        int roomsCount = inputForRoomsCount.input(flat.getFlatNumber());
        flat.setResidentsCount((int) (Math.random() * (flat.getRoomsCount() - 1 + 1) + 1));
        System.out.println("Укажите площадь каждой комнаты: ");
        for (int i = 0; i < roomsCount; i++) {
            FlatService.addRoom(flat, RoomService.createRoom()); // rooms creating
        }
        return flat;
    }

    public static @NotNull String flatInfoToString(@NotNull House house, @NotNull Flat flat) {
        String result = "";
        int houseNumber = house.getHouseNumber();
        int flatNumber = flat.getFlatNumber();
        int entranceNumber = HouseService.getEntranceByFlatNumber(house, flat.getFlatNumber()).getEntranceNumber() + 1;
        int floorNumber = HouseService.getFloorByFlatNumber(HouseService.getEntranceByFlatNumber(house, flatNumber),
                flatNumber).getFloorNumber() + 1;
        result += "\n" + GeneralConstants.SEPARATION + "\n";
        result += "Номер квартиры: " + flatNumber +
                "\nЭтаж: " + floorNumber +
                "\nНомер дома: " + houseNumber +
                "\nНомер подъезда: " + entranceNumber +
                "\nКоличество комнат: " + flat.getRoomsCount();
        for (int i = 0; i < flat.getRoomsCount(); i++) {
            result += "\nПлощадь " + (i + 1) + " комнаты: " + flat.getRooms().get(i).getRoomSquare();
        }
        result += "\nОбщая площадь квартиры: " + FlatService.findFlatSquare(flat) + "\nКоличество жильцов: "
                + flat.getResidentsCount();
        result += "\n" + GeneralConstants.SEPARATION + "\n";
        return result;
    }

    public static void addRoom(@NotNull Flat flat, @NotNull Room room) {
        flat.getRooms().add(room);
        flat.setRoomsCount(flat.getRoomsCount() + 1);
    }


}
