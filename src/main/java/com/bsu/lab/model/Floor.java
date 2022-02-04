package com.bsu.lab.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "floors")
public class Floor implements Comparable<Floor> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
        if (flatsCount > o.flatsCount) return 1;
        if (flatsCount < o.flatsCount) return -1;
        return 0;
    }
}
