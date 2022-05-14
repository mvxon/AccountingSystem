package com.bsu.lab.AccountingSystem.validators;

import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.dto.FlatDTO;
import com.bsu.lab.AccountingSystem.dto.HouseDTO;
import com.bsu.lab.AccountingSystem.dto.ResidentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class FlatValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return HouseDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HouseDTO house = (HouseDTO) target;
        for (FlatDTO flat : house.getFlatsOfOneFloor()) {
            if (flat.getRoomsCount() == null) {
                errors.rejectValue("flatsOfOneFloor", "", "All fields should be filled");
                return;
            } else {
                if (flat.getRoomsCount() < 1 || flat.getRoomsCount() > 5) {
                    errors.rejectValue("flatsOfOneFloor", "", "Rooms count should be " +
                            "between 1 and 5 numbers");
                    return;
                }
            }
        }


    }
}
