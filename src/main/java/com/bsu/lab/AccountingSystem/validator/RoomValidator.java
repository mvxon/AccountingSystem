package com.bsu.lab.AccountingSystem.validator;

import com.bsu.lab.AccountingSystem.dto.FlatDTO;
import com.bsu.lab.AccountingSystem.dto.HouseDTO;
import com.bsu.lab.AccountingSystem.dto.RoomDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RoomValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return HouseDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HouseDTO house = (HouseDTO) target;
        for (FlatDTO flat : house.getFlatsOfOneFloor()) {
            for (RoomDTO room : flat.getRooms()) {
                if (room.getRoomSquare() == null) {
                    errors.rejectValue("flatsOfOneFloor", "", "All field should be filled");
                    return;
                } else {
                    if (room.getRoomSquare() < 10.0 || room.getRoomSquare() > 50.0) {
                        errors.rejectValue("flatsOfOneFloor", "", "Room square " +
                                "should be between 10m^2 and 50m^2");
                        return;
                    }
                }
            }
        }

    }
}
