package com.nesterrovv.isdbBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class CustomerDTO {

    @NotBlank
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;

}