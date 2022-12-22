package com.nesterrovv.isdbBackend.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Accessors(chain = true)
@Data
public class Good {

    Integer good_id;
    GoodStatus status;
    Dimension dimensions;
    Integer request_id;
    Integer courier_id;

}
