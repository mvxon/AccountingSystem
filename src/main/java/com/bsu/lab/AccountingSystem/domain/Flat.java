package com.bsu.lab.AccountingSystem.domain;


import lombok.*;
import org.hibernate.annotations.Where;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Table(name = "flats")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flat implements Comparable<Flat> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int maxResidentsCount;
    private int roomsCount;
    private int flatNumber;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "flat_id")
    @OrderBy("id")
    private Set<Room> rooms;
    @OneToMany(mappedBy = "flat", fetch = FetchType.LAZY)
    @Where(clause = "accepted = true")
    private Set<User> residents;

    @PreRemove
    private void preRemove() {
        residents.forEach(resident -> resident.setFlat(null));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return flatNumber == flat.flatNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(flatNumber);
    }

    @Override
    public int compareTo(@NotNull Flat o) {
        return Integer.compare(roomsCount, o.roomsCount);
    }
}
