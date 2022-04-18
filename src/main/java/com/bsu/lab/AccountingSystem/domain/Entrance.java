package com.bsu.lab.AccountingSystem.domain;


import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "entrances",
        indexes = @Index(columnList = "entranceNumber"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Entrance implements Comparable<Entrance> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int entranceNumber;
    private int floorsCount = 0;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "entrance_id")
    @OrderBy("id")
    private Set<Floor> floors = new LinkedHashSet<>();


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


