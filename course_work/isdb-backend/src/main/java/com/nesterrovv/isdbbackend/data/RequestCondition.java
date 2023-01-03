package com.nesterrovv.isdbbackend.data;

import lombok.Data;

@Data
public class RequestCondition {

    private int request_condition_id;
    private int request_id;
    private ExtraCondition condition;

}
