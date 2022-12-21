package com.nesterrovv.isdbBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class CourierDTO {

    @NotBlank
    private Integer id;

}