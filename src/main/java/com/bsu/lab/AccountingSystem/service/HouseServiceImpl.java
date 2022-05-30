package com.bsu.lab.AccountingSystem.service;


import com.bsu.lab.AccountingSystem.domain.*;
import com.bsu.lab.AccountingSystem.dao.HouseRepository;
import com.bsu.lab.AccountingSystem.dto.FlatDTO;
import com.bsu.lab.AccountingSystem.dto.HouseDTO;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;
    private final EntranceService entranceService;
    private final FloorService floorService;
    private final FlatService flatService;

    public HouseServiceImpl(
            HouseRepository houseRepository,
            @Lazy EntranceService entranceService,
            @Lazy FloorService floorService,
            @Lazy FlatService flatService
    ) {
        this.houseRepository = houseRepository;
        this.entranceService = entranceService;
        this.floorService = floorService;
        this.flatService = flatService;
    }


    @Override
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
        house.setStatus(HouseStatus.FINISHED);
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
    public int findMaximumResidentsCapacity(@NotNull House house) {
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
        int flatsPerFloor = getFlatsPerFloor(house);
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
    public House getHouseByHouseNumber(int houseNumber) {
        return houseRepository.findByHouseNumber(houseNumber);
    }


    @Override
    public TreeSet<Integer> getUsedHouseNumbers() {
        return houseRepository.findUsedHouseNumbers();
    }

    @Override
    public List<House> getAllHouses() {
        return houseRepository.findAllFinishedHouses();
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
        for (Integer hNumber : getUsedHouseNumbers()) {
            if (Objects.equals(houseNumber, hNumber)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public List<House> getAllUnFinishedHouses() {
        return houseRepository.findAllUnFinishedHouses();
    }

    @Override
    public House firstStepSave(HouseDTO houseDTO) {
        House house = House.builder()
                .houseNumber(houseDTO.getHouseNumber())
                .entrancesCount(houseDTO.getEntrancesCount())
                .status(HouseStatus.CREATED)
                .address(Address.builder()
                        .city(houseDTO.getCity())
                        .street(houseDTO.getStreet())
                        .build())
                .build();
        Entrance entrance = Entrance.builder()
                .floorsCount(houseDTO.getFloorsCount())
                .build();
        Floor floor = Floor.builder()
                .flatsCount(houseDTO.getFlatsPerFloor())
                .build();
        entrance.setFloors(Collections.singleton(floor));
        house.setEntrances(Collections.singleton(entrance));
        houseRepository.save(house);
        return house;
    }

    @Override
    public void secondStepSave(HouseDTO houseDTO) {
        House house = houseRepository.getById(houseDTO.getHouseId());
        Set<Flat> flatSet = new LinkedHashSet<>();
        int flatNumberCounter = 0;
        for (FlatDTO flat : houseDTO.getFlatsOfOneFloor()) {
            flatSet.add(Flat.builder()
                    .flatNumber(flatNumberCounter++)
                    .roomsCount(flat.getRoomsCount())
                    .build());
        }
        house.getEntrances().iterator().next().getFloors().iterator().next().setFlats(flatSet);
        house.setStatus(HouseStatus.CONTINUED);
        houseRepository.save(house);
    }

    @Override
    public void finalStepSave(HouseDTO houseDTO) {
        House house = houseRepository.getById(houseDTO.getHouseId());
        List<List<Double>> squareOfRoomsOfFlats = new ArrayList<>();
        int flatsCountPerFloor = house.getEntrances().iterator().next().getFloors().iterator().next().getFlatsCount();
        List<Flat> flatsOfOneFloor =
                new ArrayList<>(house.getEntrances().iterator().next().getFloors().iterator().next().getFlats());
        for (int i = 0; i < flatsCountPerFloor; i++) {
            List<Double> squareOfRoomsOfOneFlat = new ArrayList<>();
            for (int j = 0; j < flatsOfOneFloor.get(i).getRoomsCount(); j++) {
                squareOfRoomsOfOneFlat.add(houseDTO.getFlatsOfOneFloor().get(i).getRooms().get(j).getRoomSquare());
            }
            squareOfRoomsOfFlats.add(squareOfRoomsOfOneFlat);
        }
        House resultHouse = createHouse(house.getHouseNumber(),
                house.getEntrancesCount(),
                house.getEntrances().iterator().next().getFloorsCount(),
                squareOfRoomsOfFlats);
        resultHouse.setId(house.getId());
        resultHouse.setAddress(house.getAddress());
        houseRepository.save(resultHouse);
    }

    @Override
    public House getHouseById(Long id) {
        return houseRepository.getById(id);
    }

    @Override
    public void deleteHouse(Long id) {
        houseRepository.deleteById(id);
    }

    @Override
    public HouseDTO houseToDto(House house) { // full dto for house info page
        return HouseDTO.builder()
                .houseId(house.getId())
                .houseNumber(house.getHouseNumber())
                .city(house.getAddress().getCity())
                .street(house.getAddress().getStreet())
                .entrancesCount(house.getEntrancesCount())
                .floorsCount(getFloorsCount(house))
                .flatsPerFloor(getFlatsPerFloor(house))
                .totalHouseSquare(findTotalHouseSquare(house))
                .maximumResidentsCapacity(findMaximumResidentsCapacity(house))
                .totalResidentsCount(findTotalResidentsCount(house))
                .flats(flatService.listOfFlatsToDto(getHouseFlats(house)))
                .build();
    }


    @Override
    public int findTotalResidentsCount(House house) {
        int result = 0;
        for (Entrance entrance : house.getEntrances()) {
            for (Floor floor : entrance.getFloors()) {
                for (Flat flat : floor.getFlats()) {
                    result += flat.getResidents().size();
                }
            }
        }
        return result;
    }

    @Override
    public void finalStepSave(House house) {
        houseRepository.save(house);
    }

    @Override
    public List<HouseDTO> allExistingHousesToDto() { // not complete dto for list of houses
        return houseRepository.findAllFinishedHouses()
                .stream()
                .map(house ->
                        HouseDTO.builder()
                                .houseId(house.getId())
                                .houseNumber(house.getHouseNumber())
                                .city(house.getAddress().getCity())
                                .street(house.getAddress().getStreet())
                                .build())
                .collect(Collectors.toList());
    }

    @Override
    public int getFlatsPerFloor(@NotNull House house) {
        return house.getEntrances().iterator().next().getFloors().iterator().next().getFlatsCount();
    }

    @Override
    public List<Flat> getHouseFlats(@NotNull House house) {
        List<Flat> result = new ArrayList<>();
        for (Entrance entrance : house.getEntrances()) {
            for (Floor floor : entrance.getFloors()) {
                result.addAll(floor.getFlats());
            }
        }
        return result;
    }


}
