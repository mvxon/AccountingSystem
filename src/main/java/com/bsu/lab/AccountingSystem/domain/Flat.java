package com.bsu.lab.AccountingSystem.domain;


import lombok.*;
import org.hibernate.annotations.Where;


import javax.persistence.*;
import java.util.Set;

@Table(name = "flats")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flat  {
    private static final String SEQ_NAME = "flat_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
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

}
