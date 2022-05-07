package com.bsu.lab.AccountingSystem.service;


import com.bsu.lab.AccountingSystem.consolecontrol.constants.GeneralConstants;
import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.domain.House;
import com.bsu.lab.AccountingSystem.domain.Resident;
import com.bsu.lab.AccountingSystem.domain.Room;

import com.bsu.lab.AccountingSystem.dao.FlatRepository;
import com.bsu.lab.AccountingSystem.dto.FlatDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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
    public Flat copyFlat(Flat flat) {
        Flat copy = new Flat();
        int roomNumberCounter = 0;
        Set<Room> rooms = new HashSet<>();
        copy.setRooms(rooms);
        for (Room room : flat.getRooms()) {
            Room roomCopy = roomService.copyRoom(room);
            roomCopy.setRoomNumber(++roomNumberCounter);
            addRoom(copy, roomCopy);
        }
        return copy;
    }

    @Override
    public @NotNull Flat createFlat(@NotNull List<Double> squareOfRoomsOfFlat) {
        Flat flat = new Flat();
        Set<Room> rooms = new HashSet<>();
        flat.setRooms(rooms);
        int roomsCount = squareOfRoomsOfFlat.size();
        flat.setMaxResidentsCount((int) (Math.random() * (flat.getRoomsCount() - 1 + 1) + 1));
        for (int i = 0; i < roomsCount; i++) {
            Room room = roomService.createRoom(squareOfRoomsOfFlat.get(i));
            room.setRoomNumber(i + 1);
            this.addRoom(flat, room); // rooms creating
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
                "\nКоличество жильцов: " + flat.getMaxResidentsCount();
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
    public Flat getFlatByResident(String username) {
        return residentService.getResidentByName(username).getFlat();
    }

    @Override
    public boolean addResident(Flat flat, Resident resident) {

        if (flat.getResidents().add(resident)) {
            flatRepository.save(flat);
            return true;
        }
        return false;
    }

    @Override
    public void save(Flat flat) {
        flatRepository.save(flat);
    }

    @Override
    public Set<Resident> getFlatResidents(Flat flat) {
        return flat.getResidents();
    }

    @Override
    public Flat getFlatById(Long id) {
        return flatRepository.findById(id);
    }

    @Override
    public FlatDTO flatToDto(Flat flat) {
        House house = houseService.getHouseByFlat(flat);
        return FlatDTO.builder()
                .flatNumber(flat.getFlatNumber())
                .flatSquare(findFlatSquare(flat))
                .residents(residentService.residentsSetToDto(flat.getResidents()))
                .residentsCount(flat.getResidents().size())
                .entranceNumber(houseService.getEntranceByFlatNumber(house, flat.getFlatNumber()).getEntranceNumber())
                .houseNumber(house.getHouseNumber())
                .floorNumber(entranceService.getFloorByFlatNumber(houseService
                                .getEntranceByFlatNumber(house, flat.getFlatNumber()),
                        flat.getFlatNumber()).getFloorNumber())
                .roomsCount(flat.getRoomsCount())
                .build();
    }


}
