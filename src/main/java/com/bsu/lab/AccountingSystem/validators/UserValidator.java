package com.bsu.lab.AccountingSystem.validators;

import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.domain.User;
import com.bsu.lab.AccountingSystem.dto.UserDTO;
import com.bsu.lab.AccountingSystem.service.HouseService;
import com.bsu.lab.AccountingSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {
    private final UserService userService;
    private final HouseService houseService;

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        UserDTO user = (UserDTO) target;
        User savedUser = null;
        boolean creation = !(user.getPassword() == null);
        if (!creation) {
            savedUser = userService.getById(user.getUserId());
        }
        if (user.getWithFlat() == null) {
            user.setWithFlat(!houseService.getAllHouses().isEmpty());
        }
        if (user.getUsername().isBlank()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "",
                    "Required field");
        } else {
            if (user.getUsername().length() < 3 || user.getUsername().length() > 32) {
                errors.rejectValue("username", "",
                        "Length of the username should be between 3 and 32 chars");
            }
            if (creation) {
                if (userService.getUserByName(user.getUsername()) != null) {
                    errors.rejectValue("username", "", "User with username "
                            + user.getUsername() + " already exists");
                }
            } else {
                if (!Objects.equals(savedUser.getName(), user.getUsername())) {
                    if (userService.getUserByName(user.getUsername()) != null) {
                        errors.rejectValue("username", "", "User with username "
                                + user.getUsername() + " already exists");
                    }
                }
            }
        }

        if (creation) {
            if (user.getPassword().isBlank()) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "",
                        "Required field");
            } else {
                if (user.getPassword().length() < 3 || user.getPassword().length() > 32) {
                    errors.rejectValue("password", "",
                            "Length of the password should be between 8 and 32 chars");
                } else {
                    if (!user.getMatchingPassword().equals(user.getPassword())) {
                        errors.rejectValue("matchingPassword", "", "Passwords are not matching");
                    }
                }
            }
        }
        if (!user.getEmail().isBlank()) {
            if (creation) {
                if (userService.getUserByEmail(user.getEmail()) != null) {
                    errors.rejectValue("email", "", "User with such email already exists");
                }
            } else {
                if (!Objects.equals(savedUser.getEmail(), user.getEmail())) {
                    if (userService.getUserByEmail(user.getEmail()) != null) {
                        errors.rejectValue("email", "", "User with such email already exists");
                    }
                }
            }
        }
        if (user.getWithFlat()) {
            if (user.getHouseNumber() == null) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "houseNumber", "",
                        "Required field");
            } else {
                if (!houseService.isHouseWithNumberExists(user.getHouseNumber())) {
                    errors.rejectValue("houseNumber", "", "House with number "
                            + user.getHouseNumber() + " does not exists");
                } else {
                    if (user.getFlatNumber() == null) {
                        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "flatNumber", "",
                                "Required field");
                    } else {
                        if (!houseService.isFlatNumberExists(houseService.getHouseByHouseNumber(user.getHouseNumber()),
                                user.getFlatNumber())) {
                            errors.rejectValue("flatNumber", "", "Flat number "
                                    + user.getFlatNumber() + " does not exists in house number " +
                                    user.getHouseNumber() + ", choose between 1 and " +
                                    houseService.getFlatsCount(houseService.getHouseByHouseNumber(user.getHouseNumber())) +
                                    " flat numbers");
                        } else {
                            Flat flat = houseService.getFlatByNumber(houseService.
                                    getHouseByHouseNumber(user.getHouseNumber()), user.getFlatNumber());
                            if (flat.getMaxResidentsCount() == flat.getResidents().size()) {
                                errors.rejectValue("flatNumber", "",
                                        "This flat is already full of residents");
                            }
                        }
                    }
                }
            }
        }
    }
}
