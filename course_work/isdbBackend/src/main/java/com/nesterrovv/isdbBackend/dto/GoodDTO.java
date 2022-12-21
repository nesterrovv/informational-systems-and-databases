package com.nesterrovv.isdbBackend.dto;

import com.nesterrovv.isdbBackend.model.GoodStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class GoodDTO {

    @NotBlank
    private Integer id;
    @NotBlank
    private GoodStatus status;
    @NotBlank
    private Integer courier_id;

}