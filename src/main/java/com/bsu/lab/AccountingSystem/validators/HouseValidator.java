package com.bsu.lab.AccountingSystem.validators;

import com.bsu.lab.AccountingSystem.dto.HouseDTO;
import com.bsu.lab.AccountingSystem.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class HouseValidator implements Validator {
    private final HouseService houseService;

    @Override
    public boolean supports(Class<?> clazz) {
        return HouseDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HouseDTO house = (HouseDTO) target;
        if (house.getHouseNumber() != null) {
            for (Integer houseNumber : houseService.getUsedHouseNumbers()) {
                if (Objects.equals(house.getHouseNumber(), houseNumber)) {
                    errors.rejectValue("houseNumber", "", "House with number " + houseNumber +
                            " already exists");
                }
            }
        }
    }
}
