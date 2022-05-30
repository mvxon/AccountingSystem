package com.bsu.lab.AccountingSystem.domain;


import lombok.*;


import javax.persistence.*;
import java.util.Set;

@Table(name = "entrances")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Entrance {
    private static final String SEQ_NAME = "entrance_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    private int entranceNumber;
    private int floorsCount;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "entrance_id")
    @OrderBy("id")
    private Set<Floor> floors;

}


