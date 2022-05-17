package com.bsu.lab.AccountingSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlatDTO {
    private Long houseId;
    private Long flatId;
    private Integer houseNumber;
    private Integer entranceNumber;
    private Integer floorNumber;
    private Integer flatNumber;
    private Integer residentsCount;
    private Set<ResidentDTO> residents;
    private List<RoomDTO> rooms;
    private Double flatSquare;
    private Integer roomsCount = 1;


}
