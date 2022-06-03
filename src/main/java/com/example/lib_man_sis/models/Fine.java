package com.example.lib_man_sis.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "fineId")
public class Fine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fineId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "reservationId", insertable = false, updatable = false)
    private Reservation reservation;
    private Long reservationId;
    private String status;
    private double fee;
    public Fine(Long reservationId) {
        this.reservationId = reservationId;
    }
}