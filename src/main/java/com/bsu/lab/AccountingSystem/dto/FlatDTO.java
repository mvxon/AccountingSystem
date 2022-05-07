package com.bsu.lab.AccountingSystem.dto;

import com.bsu.lab.AccountingSystem.domain.Resident;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlatDTO {
    private Integer houseNumber;
    private Integer entranceNumber;
    private Integer floorNumber;
    private Integer flatNumber;
    private Integer residentsCount;
    private Set<ResidentDTO> residents;
    private Double flatSquare;
    private Integer roomsCount;


}
