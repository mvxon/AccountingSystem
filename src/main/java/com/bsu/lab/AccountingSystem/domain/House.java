package com.bsu.lab.AccountingSystem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Table(name = "houses",
        indexes = @Index(columnList = "houseNumber"))
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class House implements Comparable<House> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private int houseNumber;
    private int entrancesCount;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    @OrderBy("id")
    private Set<Entrance> entrances;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @Enumerated(EnumType.STRING)
    private HouseStatus status;

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
