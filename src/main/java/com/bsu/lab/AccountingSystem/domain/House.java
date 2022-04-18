package com.bsu.lab.AccountingSystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "houses",
        indexes = @Index(columnList = "houseNumber"))
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class House implements Comparable<House> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private int houseNumber;
    private int entrancesCount = 0;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "house_id")
    @OrderBy("id")
    private Set<Entrance> entrances = new LinkedHashSet<>();
    @OneToOne
    @JoinColumn(name = "address_id")
    Address address;

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
        return Integer.compare(entrancesCount, o.entrancesCount);
    }
}
