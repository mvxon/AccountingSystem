package com.bsu.lab.AccountingSystem.dto;


import com.bsu.lab.AccountingSystem.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResidentDTO {
    private Long residentId;
    private Long flatId;
    private String username;
    private String password;
    private String matchingPassword;
    @NotEmpty(message = "Required field")
    @Email(message = "Email should be valid")
    private String email;
    private Integer houseNumber;
    private Integer flatNumber;
    @NotEmpty(message = "Required field")
    private String city;
    @NotEmpty(message = "Required field")
    private String street;
    private Role role;


}