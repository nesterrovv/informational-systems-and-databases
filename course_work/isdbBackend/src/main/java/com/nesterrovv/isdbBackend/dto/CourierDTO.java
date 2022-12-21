package com.nesterrovv.isdbBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class CourierDTO {

    @NotBlank
    private Integer courier_id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    // two fileds of DTO which can be null in case if courier are logging in
    private String name;
    private String surname;

}