package com.bsu.lab.AccountingSystem.service;


import com.bsu.lab.AccountingSystem.constants.GeneralConstants;
import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.domain.House;
import com.bsu.lab.AccountingSystem.domain.Resident;
import com.bsu.lab.AccountingSystem.domain.Room;

import com.bsu.lab.AccountingSystem.dao.FlatRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class FlatServiceImpl implements FlatService {
    private final HouseService houseService;
    private final ResidentService residentService;
    private final EntranceService entranceService;
    private final RoomService roomService;
    private final FlatRepository flatRepository;

    @Autowired
    public FlatServiceImpl(
            @Lazy HouseService houseService,
            ResidentService residentService,
            EntranceService entranceService,
            RoomService roomService,
            FlatRepository flatRepository) {
        this.houseService = houseService;
        this.residentService = residentService;
        this.entranceService = entranceService;
        this.roomService = roomService;
        this.flatRepository = flatRepository;
    }

    @Override
    public double findFlatSquare(@NotNull Flat flat) {
        double result = 0;
        for (Room room : flat.getRooms()) {
            result += room.getRoomSquare();
        }
        return result;
    }

    @Override
    public @NotNull Flat createFlat(@NotNull List<Double> squareOfRoomsOfFlat) {
        Flat flat = new Flat();
        flat.setFlatNumber();
        int roomsCount = squareOfRoomsOfFlat.size();
        flat.setResidentsCount((int) (Math.random() * (flat.getRoomsCount() - 1 + 1) + 1));

        for (int i = 0; i < roomsCount; i++) {
            this.addRoom(flat, roomService.createRoom(squareOfRoomsOfFlat.get(i))); // rooms creating
        }
        return flat;
    }

    @Override
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
        result += "\nОбщая площадь квартиры: " + this.findFlatSquare(flat) +
                "\nКоличество жильцов: " + flat.getResidentsCount();
        result += "\n" + GeneralConstants.SEPARATION + "\n";
        return result;
    }

    @Override
    public void addRoom(@NotNull Flat flat, @NotNull Room room) {
        if (flat.getRooms().add(room)) {
            flat.setRoomsCount(flat.getRoomsCount() + 1);
        }
    }

    @Override
    @Transactional
    public void deleteResident(Flat flat, Long residentId) {
        Set<Resident> residents = flat.getResidents();
        Set<Resident> newResidentsSet = new HashSet<>();
        boolean flag = false;
        for (Resident resident : residents) {
            if (Objects.equals(resident.getId(), residentId) && !flag) {
                flag = true;
                residentService.moveOutFromFlat(resident);
                continue;
            }
            newResidentsSet.add(resident);
        }
        flat.setResidents(newResidentsSet);
        flatRepository.save(flat);
    }

    @Override
    @Transactional
    public Flat getFlatByResident(String username) {
        return residentService.findByName(username).getFlat();
    }


}
