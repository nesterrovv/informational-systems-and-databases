package com.nesterrovv.isdbbackend.application;

import com.nesterrovv.isdbbackend.data.GoodStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
class GoodDTO {
    private GoodStatus status;
    private double weight;
    private double length;
    private double width;
    private double height;
    private String description;
}
