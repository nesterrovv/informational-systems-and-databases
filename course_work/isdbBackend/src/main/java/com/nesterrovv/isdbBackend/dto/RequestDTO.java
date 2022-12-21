package com.nesterrovv.isdbBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class RequestDTO {

    @NotBlank
    private Integer id;

}