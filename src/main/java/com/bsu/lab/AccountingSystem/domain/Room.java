package com.bsu.lab.AccountingSystem.domain;


import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "rooms")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private static final String SEQ_NAME = "room_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    private int roomNumber;
    private double roomSquare;

}

