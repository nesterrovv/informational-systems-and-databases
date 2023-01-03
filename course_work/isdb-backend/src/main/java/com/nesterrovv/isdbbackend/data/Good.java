package com.nesterrovv.isdbbackend.data;

import lombok.Data;

@Data
public class Good {

    private int good_id;
    private GoodStatus status;
    private Dimensions dimensions;
    private int request_id;
}
