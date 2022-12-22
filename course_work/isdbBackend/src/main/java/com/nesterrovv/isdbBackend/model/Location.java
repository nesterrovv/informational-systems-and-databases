package com.nesterrovv.isdbBackend.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
public class Location {
    Integer location_id;
    String nane;

}
