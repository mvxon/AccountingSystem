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
    Integer houseNumber;
    Integer entranceNumber;
    Integer floorNumber;
    Integer flatNumber;
    Integer residentsCount;
    Set<Resident> residents;
    Double flatSquare;
    Integer roomsCount;


}
