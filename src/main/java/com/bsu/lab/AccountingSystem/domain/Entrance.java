package com.bsu.lab.AccountingSystem.domain;


import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Table(name = "entrances")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Entrance implements Comparable<Entrance> {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrance entrance = (Entrance) o;
        return entranceNumber == entrance.entranceNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(entranceNumber);
    }


    @Override
    public int compareTo(@NotNull Entrance o) {
        return Integer.compare(floorsCount, o.floorsCount);
    }
}


