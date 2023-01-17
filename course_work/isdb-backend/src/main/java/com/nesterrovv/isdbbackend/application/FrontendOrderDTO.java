package com.nesterrovv.isdbbackend.application;

import com.nesterrovv.isdbbackend.data.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FrontendOrderDTO {
    private int customer_id;
    private String departure_point;
    private String description_point;
    private OrderStatus status;
    private String description;
    private List<GoodDTO> goods;
}

