package com.nesterrovv.isdbBackend.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Accessors(chain = true)
@Entity
@Data
@Table(name = "good")
public class Good {

    @Id
    @Column
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    Integer good_id;
    @Column
    GoodStatus status;
    @Column
    Dimension dimensions;
    @Column
    Integer request_id;
    @Column
    Integer courier_id;

}
