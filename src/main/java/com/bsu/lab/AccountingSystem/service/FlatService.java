package com.bsu.lab.AccountingSystem.service;


import com.bsu.lab.AccountingSystem.constant.GeneralConstants;
import com.bsu.lab.AccountingSystem.model.Flat;
import com.bsu.lab.AccountingSystem.model.House;
import com.bsu.lab.AccountingSystem.model.Room;
import com.bsu.lab.AccountingSystem.util.input.service.InputForRoomsCount;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlatService {
    private final HouseService houseService;
    private final EntranceService entranceService;
    private final RoomService roomService;
    private final InputForRoomsCount inputForRoomsCount;

    @Autowired
    public FlatService(
            HouseService houseService,
            EntranceService entranceService,
            RoomService roomService,
            InputForRoomsCount inputForRoomsCount) {
        this.houseService = houseService;
        this.entranceService = entranceService;
        this.roomService = roomService;
        this.inputForRoomsCount = inputForRoomsCount;
    }

    public double findFlatSquare(@NotNull Flat flat) {
        double result = 0;
        for (Room room : flat.getRooms()) {
            result += room.getRoomSquare();
        }
        return result;
    }

    public @NotNull Flat createFlat() {
        Flat flat = new Flat();
        flat.setFlatNumber();
        int roomsCount = inputForRoomsCount.input(flat.getFlatNumber());
        flat.setResidentsCount((int) (Math.random() * (flat.getRoomsCount() - 1 + 1) + 1));
        System.out.println("Укажите площадь каждой комнаты: ");
        for (int i = 0; i < roomsCount; i++) {
            this.addRoom(flat, roomService.createRoom()); // rooms creating
        }
        return flat;
    }

    public @NotNull String flatInfoToString(@NotNull House house, @NotNull Flat flat) {
        String result = "";
        int houseNumber = house.getHouseNumber();
        int flatNumber = flat.getFlatNumber();
        int entranceNumber = houseService.getEntranceByFlatNumber(house, flat.getFlatNumber()).getEntranceNumber();
        int floorNumber = entranceService.getFloorByFlatNumber(houseService.getEntranceByFlatNumber(house, flatNumber),
                flatNumber).getFloorNumber();
        result += "\n" + GeneralConstants.SEPARATION + "\n";
        result += "Номер квартиры: " + flatNumber +
                "\nНомер дома: " + houseNumber +
                "\nНомер подъезда: " + entranceNumber +
                "\nЭтаж: " + floorNumber +
                "\nКоличество комнат: " + flat.getRoomsCount();
        for (Room room : flat.getRooms()) {
            result += "\nПлощадь " + (room.getRoomNumber()) + " комнаты: " + room.getRoomSquare();
        }
        result += "\nОбщая площадь квартиры: " + this.findFlatSquare(flat) + "\nКоличество жильцов: "
                + flat.getResidentsCount();
        result += "\n" + GeneralConstants.SEPARATION + "\n";
        return result;
    }

    public void addRoom(@NotNull Flat flat, @NotNull Room room) {
        if (flat.getRooms().add(room)) {
            flat.setRoomsCount(flat.getRoomsCount() + 1);
        }
    }


}
