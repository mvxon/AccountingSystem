package com.bsu.lab.AccountingSystem.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "residents")
public class Resident {
    private static final String SEQ_NAME = "user_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME,  allocationSize = 1)
    private Long id;
    private String name;
    private String password;
    private String email;
    private boolean accepted;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne
    @JoinColumn(name = "resident_id")
    private Flat flat;

}
