package com.example.lib_man_sis.repos;

import com.example.lib_man_sis.models.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FineRepository extends JpaRepository<Fine, Long> {

    @Query(value = "SELECT * FROM fine WHERE reservation_ID = :reservationID", nativeQuery = true)
    Fine findFineWithReservation(@Param("reservationID") long reservationId);
}
