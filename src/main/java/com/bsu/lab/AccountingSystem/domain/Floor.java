package com.bsu.lab.AccountingSystem.domain;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "floors",
        indexes = @Index(columnList = "floorNumber"))
@Entity
@Data
@NoArgsConstructor
public class Floor implements Comparable<Floor> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int floorNumber;
    private static int floorNumberCounter;
    private int flatsCount = 0;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "floor_id")
    @OrderBy("id")
    private Set<Flat> flats = new LinkedHashSet<>();


    public void setFloorNumber() {
        this.floorNumber = floorNumberCounter;
        floorNumberCounter++;
    }

    // copy constructor
    public Floor(@NotNull Floor floor) {
        this.floorNumber = floorNumberCounter;
        this.flatsCount = floor.flatsCount;
        floorNumberCounter++;
        for (Flat flat : floor.flats) {
            this.flats.add(new Flat(flat));
        }
    }

    public static void nullifyFloorNumberCounter() {
        floorNumberCounter = 1;
    }

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

