package com.bsu.lab.AccountingSystem.dto;

import com.bsu.lab.AccountingSystem.domain.Address;
import com.bsu.lab.AccountingSystem.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String email;
    private Integer houseNumber;
    private Integer flatNumber;
    private String city;
    private String street;
    private Role role;

}