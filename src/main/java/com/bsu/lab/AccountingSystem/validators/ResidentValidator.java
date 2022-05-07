package com.bsu.lab.AccountingSystem.validators;

import com.bsu.lab.AccountingSystem.dto.ResidentDTO;
import com.bsu.lab.AccountingSystem.service.HouseService;
import com.bsu.lab.AccountingSystem.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
@RequiredArgsConstructor
public class ResidentValidator implements Validator {
    private final ResidentService residentService;
    private final HouseService houseService;

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return ResidentDTO.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        ResidentDTO resident = (ResidentDTO) target;
        if (resident.getUsername().isBlank()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "",
                    "Required field");
        } else {
            if (resident.getUsername().length() < 3 || resident.getUsername().length() > 32) {
                errors.rejectValue("username", "",
                        "Length of the username should be between 3 and 32 chars");
            }
            if (residentService.getResidentByName(resident.getUsername()) != null) {
                errors.rejectValue("username", "", "User with username "
                        + resident.getUsername() + " already exists");
            }
        }

        if (resident.getPassword().isBlank()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "",
                    "Required field");
        } else {
            if (resident.getPassword().length() < 3 || resident.getPassword().length() > 32) {
                errors.rejectValue("password", "",
                        "Length of the password should be between 8 and 32 chars");
            }

            if (!resident.getMatchingPassword().equals(resident.getPassword())) {
                errors.rejectValue("matchingPassword", "", "Passwords are not matching");
            }
        }


        if (resident.getHouseNumber() == null) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "houseNumber", "",
                    "Required field");
        } else {
            if (!houseService.isHouseWithNumberExists(resident.getHouseNumber())) {
                errors.rejectValue("houseNumber", "", "House with number "
                        + resident.getHouseNumber() + " does not exists");
            } else {
                if (resident.getFlatNumber() == null) {
                    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "flatNumber", "",
                            "Required");
                } else {
                    if (!houseService.isFlatNumberExists(houseService.getHouseByHouseNumber(resident.getHouseNumber()),
                            resident.getFlatNumber())) {
                        errors.rejectValue("flatNumber", "", "Flat number "
                                + resident.getFlatNumber()
                                + " does not exists in house number " + resident.getHouseNumber());
                    }
                }
            }
        }
        if (!resident.getEmail().isBlank()) {
            if (residentService.getResidentByEmail(resident.getEmail()) != null) {
                errors.rejectValue("email", "", "User with such email already exists");
            }
        }
    }
}
