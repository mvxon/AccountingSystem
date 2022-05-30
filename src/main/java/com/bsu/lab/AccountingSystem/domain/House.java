package com.bsu.lab.AccountingSystem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Table(name = "houses")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class House {
    private static final String SEQ_NAME = "house_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    @Column(nullable = false, unique = true)
    private int houseNumber;
    private int entrancesCount;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    @OrderBy("id")
    private Set<Entrance> entrances;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @Enumerated(EnumType.STRING)
    private HouseStatus status;

}
