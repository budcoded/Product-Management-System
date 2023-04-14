package com.inventory.productmanagementsystem.PayLoad;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private Long mobileNumber;
    private String email;
    private String password;
    private String address;
    private Integer role;
}
