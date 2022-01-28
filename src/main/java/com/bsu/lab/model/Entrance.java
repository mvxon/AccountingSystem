package com.bsu.lab.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Entrance {
    @Id
    @GeneratedValue
    private int id;
    private static int entranceNumberCounter;
    private int entranceNumber;
    private int floorsCount = 0;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "entrance_id")
    private List<Floor> floors = new ArrayList<>();

    public Entrance() {
        Floor.NullifyFloorNumberCounter();
    }

    public void setEntranceNumber() {
        this.entranceNumber = entranceNumberCounter;
        entranceNumberCounter++;

    }

    // copy constructor
    public Entrance(@NotNull Entrance entrance) {
        this.entranceNumber = entranceNumberCounter;
        entranceNumberCounter++;
        Floor.NullifyFloorNumberCounter();
        this.floorsCount = entrance.floorsCount;
        for (int i = 0; i < this.floorsCount; i++) {
            this.floors.add(new Floor(entrance.floors.get(0)));
        }
    }


    public static void NullifyEntranceNumberCounter() {
        entranceNumberCounter = 0;
    }

}


