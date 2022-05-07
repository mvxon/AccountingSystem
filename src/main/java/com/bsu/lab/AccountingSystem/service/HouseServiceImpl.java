package com.bsu.lab.AccountingSystem.service;


import com.bsu.lab.AccountingSystem.consolecontrol.constants.GeneralConstants;
import com.bsu.lab.AccountingSystem.domain.*;
import com.bsu.lab.AccountingSystem.dao.HouseRepository;
import com.bsu.lab.AccountingSystem.consolecontrol.inputs.services.InputForHouseNumber;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;
    private final EntranceService entranceService;
    private final FloorService floorService;
    private final InputForHouseNumber inputForHouseNumber;

    public HouseServiceImpl(HouseRepository houseRepository,
                            @Lazy EntranceService entranceService,
                            @Lazy FloorService floorService,
                            InputForHouseNumber inputForHouseNumber) {
        this.houseRepository = houseRepository;
        this.entranceService = entranceService;
        this.floorService = floorService;
        this.inputForHouseNumber = inputForHouseNumber;
    }


    @Override
    @Transactional
    public @NotNull House createHouse(int houseNumber,
                                      int entrancesCount,
                                      int floorsCount,
                                      List<List<Double>> squareOfRoomsOfFlats
    ) {
        House house = new House();
        house.setHouseNumber(houseNumber);
        Set<Entrance> entrances = new HashSet<>();
        house.setEntrances(entrances);
        int entrancesCounter = 0;
        while (house.getEntrancesCount() < entrancesCount) {
            Entrance entrance;
            if (house.getEntrancesCount() == 0) {
                entrance = entranceService.createEntrance(floorsCount, squareOfRoomsOfFlats);
            } else {
                entrance = entranceService.copyEntrance(house.getEntrances().iterator().next());
            }
            entrance.setEntranceNumber(++entrancesCounter);
            addEntrance(house, entrance);
        }
        numerateHouseFlats(house);
        houseRepository.save(house);
        return house;
    }

    private void numerateHouseFlats(@NotNull House house) {
        int flatNumber = 1;
        for (Entrance entrance : house.getEntrances()) {
            for (Floor floor : entrance.getFloors()) {
                for (Flat flat : floor.getFlats()) {
                    flat.setFlatNumber(flatNumber);
                    flatNumber++;
                }
            }
        }
    }

    @Override
    public double findTotalHouseSquare(@NotNull House house) {
        double result = 0;
        for (Entrance entrance : house.getEntrances()) {
            for (Floor floor : entrance.getFloors()) {
                for (Flat flat : floor.getFlats()) {
                    for (Room room : flat.getRooms()) {
                        result += room.getRoomSquare();
                    }
                }
            }
        }
        return result;
    }

    @Override
    public int findTotalHouseResidentsCount(@NotNull House house) {
        int result = 0;
        for (Entrance entrance : house.getEntrances()) {
            for (Floor floor : entrance.getFloors()) {
                for (Flat flat : floor.getFlats()) {
                    result += flat.getMaxResidentsCount();
                }
            }
        }
        return result;
    }

    @Override
    public Entrance getEntranceByFlatNumber(@NotNull House house, int flatNumber) {
        for (Entrance entrance : house.getEntrances()) {
            for (Floor floor : entrance.getFloors()) {
                for (Flat flat : floor.getFlats()) {
                    if (flat.getFlatNumber() == flatNumber) {
                        return entrance;
                    }
                }
            }
        }
        return new Entrance();
    }

    @Override
    public String allHouseInfoToString(@NotNull House house) {
        String result = "\n" + GeneralConstants.SEPARATION +
                "\nНомер дома: " + (house.getHouseNumber()) +
                "\nКоличество подъездов: " + (house.getEntrancesCount());
        if (!house.getEntrances().isEmpty()) {
            result +=
                    "\nКоличество этажей: " + getFloorsCount(house) +
                            "\nКоличество квартир на одном этаже: " + getFlatsPerFloor(house) +
                            "\nОбщее количество квартир: " + getFlatsCount(house) +
                            "\nОбщая площадь дома: " + (findTotalHouseSquare(house)) +
                            "\nОбщее количество жильцов: " + (findTotalHouseResidentsCount(house)) +
                            "\n" + GeneralConstants.SEPARATION + "\n";
        }
        return result;
    }


    @Override
    public Flat getFlatByNumber(House house, int flatNumber) {
        for (Entrance entrance : house.getEntrances()) {
            for (Floor floor : entrance.getFloors()) {
                for (Flat flat : floor.getFlats()) {
                    if (flat.getFlatNumber() == flatNumber) {
                        return flat;
                    }
                }
            }
        }
        return new Flat();
    }


    @Override
    public int getFlatsCount(@NotNull House house) {
        int entrancesCount = house.getEntrancesCount();
        int flatsPerFloor = house.getEntrances().iterator().next().getFloors().iterator().next().getFlatsCount();
        int floorsCount = house.getEntrances().iterator().next().getFloorsCount();

        return entrancesCount * flatsPerFloor * floorsCount;
    }

    @Override
    public void addEntrance(@NotNull House house, @NonNull Entrance entrance) {
        if (house.getEntrances().add(entrance)) {
            house.setEntrancesCount(house.getEntrancesCount() + 1);
        }
    }

    @Override
    public int getFloorsCount(@NotNull House house) {
        return house.getEntrances().iterator().next().getFloorsCount();
    }

    @Override
    public int generateUniqueHouseNumber() {
        int houseNumber = inputForHouseNumber.input();
        Set<Integer> usedHouseNumbers = new TreeSet<>(houseRepository.findUsedHouseNumbers());
        if (houseRepository.count() != 0) {
            while (usedHouseNumbers.contains(houseNumber)) {
                System.out.println("Дом с таким номером уже создан. Введите еще раз");
                houseNumber = inputForHouseNumber.input();
            }
        }
        return houseNumber;
    }


    @Override
    public void deleteHouseByHouseNumber(int houseNumber) {
        houseRepository.deleteHouseByHouseNumber(houseNumber);
    }


    @Override
    public House getHouseByHouseNumber(int houseNumber) {
        return houseRepository.findByHouseNumber(houseNumber);
    }


    @Override
    public TreeSet<Integer> findUsedHouseNumbers() {
        return houseRepository.findUsedHouseNumbers();
    }


    @Override
    public Long getHousesCount() {
        return houseRepository.count();
    }


    @Override
    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    @Override
    public boolean isFlatNumberExists(House house, int flatNumber) {
        for (Entrance entrance : house.getEntrances()) {
            for (Floor floor : entrance.getFloors()) {
                for (Flat flat : floor.getFlats()) {
                    if (flat.getFlatNumber() == flatNumber) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public House getHouseByFlat(Flat flat) {
        return houseRepository
                .getByEntrancesContains(entranceService
                        .getEntranceByFloor(floorService
                                .getFloorByFlat(flat)));
    }

    @Override
    public boolean isHouseWithNumberExists(int houseNumber) {
        for (Integer hNumber : findUsedHouseNumbers()) {
            if (Objects.equals(houseNumber, hNumber)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getFlatsPerFloor(@NotNull House house) {
        return house.getEntrances().iterator().next().getFloors().iterator().next().getFlatsCount();
    }

    @Override
    public Set<Flat> getHouseFlats(@NotNull House house) {
        Set<Flat> result = new HashSet<>();
        for (Entrance entrance : house.getEntrances()) {
            for (Floor floor : entrance.getFloors()) {
                result.addAll(floor.getFlats());
            }
        }
        return result;
    }


}
