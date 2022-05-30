package com.bsu.lab.AccountingSystem.domain;


import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Table(name = "floors")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Floor implements Comparable<Floor> {
    private static final String SEQ_NAME = "floor_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    private int floorNumber;
    private int flatsCount;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id")
    @OrderBy("id")
    private Set<Flat> flats;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Floor floor = (Floor) o;
        return floorNumber == floor.floorNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(floorNumber);
    }

    @Override
    public int compareTo(@NotNull Floor o) {
        return Integer.compare(flatsCount, o.flatsCount);
    }
}

