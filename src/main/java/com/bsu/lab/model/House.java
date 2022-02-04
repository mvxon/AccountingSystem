package com.bsu.lab.model;

import com.bsu.lab.constant.GeneralConstants;
import com.bsu.lab.service.HouseService;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;


import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "houses")
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

    public String toString() {
        String result = "\n" + GeneralConstants.SEPARATION +
                "\nНомер дома: " + (this.houseNumber) +
                "\nКоличество подъездов: " + (this.entrancesCount);
        if (!this.entrances.isEmpty()) {
            result +=
                    "\nКоличество этажей: " + (this.entrances.iterator().next().getFloorsCount()) +
                            "\nКоличество квартир на одном этаже: "
                            + this.entrances.iterator().next().getFloors().iterator().next().getFlatsCount() +
                            "\nОбщее количество квартир: " + HouseService.getFlatsCount(this) +
                            "\nОбщая площадь дома: " + (HouseService.findTotalHouseSquare(this)) +
                            "\nОбщее количество жильцов: " + (HouseService.findTotalHouseResidentsCount(this)) +
                            "\n" + GeneralConstants.SEPARATION + "\n";
        }
        return result;
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
