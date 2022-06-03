package com.example.lib_man_sis.services;

import com.example.lib_man_sis.models.Fine;
import com.example.lib_man_sis.repos.FineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FineService {

    @Autowired
    private FineRepository fineRepository;

    public List<Fine> getAllFines() {
        return fineRepository.findAll();
    }

    //Add new book to database
    public void save(Fine fine) {
        fineRepository.save(fine);
    }

    public Optional<Fine> findById(long id) {
        return fineRepository.findById(id);
    }

    public Optional<Fine> findFineAlreadyInDb(long id) {
        return Optional.ofNullable(fineRepository.findFineWithReservation(id));
    }

}
