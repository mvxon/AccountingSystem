package com.bsu.lab.AccountingSystem.entities;


import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Table(indexes = @Index(columnList = "entranceNumber"))
@Getter
@Setter
@Entity
public class Entrance implements Comparable<Entrance> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private static int entranceNumberCounter;
    private int entranceNumber;
    private int floorsCount = 0;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "entrance_id")
    @OrderBy("id")
    private Set<Floor> floors = new LinkedHashSet<>();

    public Entrance() {
        Floor.nullifyFloorNumberCounter();
    }

    public void setEntranceNumber() {
        this.entranceNumber = entranceNumberCounter;
        entranceNumberCounter++;

    }

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

    // copy constructor
    public Entrance(@NotNull Entrance entrance) {
        this.entranceNumber = entranceNumberCounter;
        entranceNumberCounter++;
        Floor.nullifyFloorNumberCounter();
        this.floorsCount = entrance.floorsCount;
        for (Floor floor : entrance.floors) {
            this.floors.add(new Floor(floor));
        }
    }


    public static void nullifyEntranceNumberCounter() {
        entranceNumberCounter = 1;
    }

    @Override
    public int compareTo(@NotNull Entrance o) {
        if (floorsCount > o.floorsCount) return 1;
        if (floorsCount < o.floorsCount) return -1;
        return 0;
    }
}


