package com.bsu.lab.AccountingSystem.model;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;


import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
public class House implements Comparable<House> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private int houseNumber;

    private int entrancesCount = 0;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "house_id")
    @OrderBy("id")
    private Set<Entrance> entrances = new LinkedHashSet<>();



    public House() {
        Entrance.nullifyEntranceNumberCounter();
        Flat.nullifyFlatNumberCounter();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return houseNumber == house.houseNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseNumber);
    }


    @Override
    public int compareTo(@NotNull House o) {
        House house = (House) o;
        if (entrancesCount > house.entrancesCount) {
            return 1;
        }
        if (entrancesCount < house.entrancesCount) {
            return -1;
        }
        return 0;
    }
}
