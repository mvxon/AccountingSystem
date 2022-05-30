package com.bsu.lab.AccountingSystem.validator;

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
        if (!house.getCity().isEmpty()) {
            if (house.getCity().length() < 3 || house.getCity().length() > 25) {
                errors.rejectValue("city", "", "City should be between 3 and 25 chars");
            }
        }
        if (!house.getStreet().isEmpty()) {
            if (house.getStreet().length() < 3 || house.getStreet().length() > 25) {
                errors.rejectValue("street", "", "Street should be between 3 and 25 chars");
            }
        }
    }
}