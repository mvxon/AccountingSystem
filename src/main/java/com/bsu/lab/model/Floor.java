package com.bsu.lab.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Floor {
    @Id
    @GeneratedValue
    private int id;

    private int floorNumber;
    private static int floorNumberCounter;
    private int flatsCount = 0;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "floor_id")
    private List<Flat> flats = new ArrayList<>();


    public void setFloorNumber() {
        this.floorNumber = floorNumberCounter;
        floorNumberCounter++;
    }

    // copy constructor
    public Floor(@NotNull Floor floor) {
        this.floorNumber = floorNumberCounter;
        this.flatsCount = floor.flatsCount;
        floorNumberCounter++;
        for (int i = 0; i < floor.flatsCount; i++) {
            this.flats.add(new Flat(floor.flats.get(i)));
        }
    }

    public static void NullifyFloorNumberCounter() {
        floorNumberCounter = 0;
    }

}
