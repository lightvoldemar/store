package com.pillikan.store.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity implements HasId {
    public static final int START_SEQ = 10000;

    @Id
    @SequenceGenerator(allocationSize = 1, sequenceName = "global_seq", name = "global_seq" )
    @GeneratedValue(generator = "global_seq",strategy = GenerationType.SEQUENCE)
    private Integer id;
}
