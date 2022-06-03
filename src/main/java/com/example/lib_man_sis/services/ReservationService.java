package com.example.lib_man_sis.services;

import com.example.lib_man_sis.models.Reservation;
import com.example.lib_man_sis.repos.BookRepository;
import com.example.lib_man_sis.repos.ReservationRepository;
import com.example.lib_man_sis.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository memberRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> findById(long id) {
        return reservationRepository.findById(id);
    }

    public void reserveBook(Reservation reservation) {
        reservationRepository.save(reservation);
    }
}
