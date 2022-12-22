package com.nesterrovv.isdbBackend.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
public class Request {

    Integer request_id;
    Integer order_id;
    String description;
    Double weight;
    Double length;
    Double width;
    Double height;

}
