package com.bsu.lab.AccountingSystem.dto;

import com.bsu.lab.AccountingSystem.domain.Flat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HouseDTO {
    private Long houseId;
    @NotNull(message = "Required field")
    @Min(value = 1, message = "House number should be between 1 and 100 numbers")
    @Max(value = 100, message = "House number should be between 1 and 100 numbers")
    private Integer houseNumber;
    @NotNull(message = "Required field")
    @Min(value = 1, message = "Entrances count should be between 1 and 5 numbers")
    @Max(value = 5, message = "Entrances count should be between 1 and 5 numbers")
    private Integer entrancesCount;
    @NotNull(message = "Required field")
    @Min(value = 1, message = "Floors count should be between 1 and 50 numbers")
    @Max(value = 50, message = "Floors count should be between 1 and 50 numbers")
    private Integer floorsCount;
    @NotNull(message = "Required field")
    @Min(value = 1, message = "Count of flats on the floor should be between 1 and 5 numbers")
    @Max(value = 5, message = "Count of flats on the floor should be between 1 and 5 numbers")
    private Integer flatsPerFloor;
    private List<FlatDTO> flatsOfOneFloor;
    @NotEmpty(message = "Required field")
    private String street;
    @NotEmpty(message = "Required field")
    private String city;

}
