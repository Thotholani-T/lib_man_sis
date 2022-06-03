package com.example.lib_man_sis.controllers;

import com.example.lib_man_sis.models.Book;
import com.example.lib_man_sis.models.Fine;
import com.example.lib_man_sis.models.Reservation;
import com.example.lib_man_sis.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private FineController fineController;

    @Autowired
    private FineService fineService;

    @RequestMapping(value="/catalogue/reserve")
    public String reserve(Long id) {
        //setting dates
        LocalDate borrowDate = LocalDate.now();
        LocalDate returnDate = borrowDate.minusDays(7);

        Book bookSelected = bookService.findById(id).get();
        int numberOfCopiesAvailable = bookSelected.getCopies();

        if (numberOfCopiesAvailable > 0) {
//          Book is available and thus can be reserved
//          numberOfCopiesAvailable--;
//          bookSelected.setCopies(numberOfCopiesAvailable);
            var reservation = new Reservation();
            reservation.setBookId(bookSelected.getId());
            reservation.setBorrowDate(borrowDate);
            reservation.setReturnDate(returnDate);
            reservation.setStatus("Reserved");
            reservationService.reserveBook(reservation);
            return "redirect:/catalogue";
        }
//        if (reservationService.findById(id).get().getStatus() == "Canceled" || reservationService.findById(id).get().getStatus() == "Returned") {
//            bookSelected.setCopies(bookSelected.getCopies()+1);
//            bookService.save(bookSelected);
//        }
        return "redirect:/catalogues";
    }

    @GetMapping("/reservations")
    public String listReservations(Model model) {
        List<Reservation> reservationList = reservationService.getAllReservations();
        model.addAttribute("reservations", reservationList);

        for (int i = 0; i < reservationList.size(); i++) {
            if (LocalDate.now().isAfter(reservationList.get(i).getReturnDate())){
                reservationList.get(i).setStatus("Overdue");
                //save the status to the changed one
                reservationService.reserveBook(reservationList.get(i));

                Fine fine = new Fine();
                fine.setReservationId(reservationList.get(i).getReservationId());
                fine.setFee(20);
                fine.setStatus("pending");

                Optional<Fine> existingFine = fineService.findFineAlreadyInDb(reservationList.get(i).getReservationId());

                //check if reservation has already been fined
                if (existingFine.isPresent()) {
                    System.out.println("The fine with that reservationId is already in the system");
                } else {
                    System.out.println("Saving fine");
                    fineService.save(fine);
                    emailService.sendOverdueEmail("tembothotholani@gmail.com");
                }
            }
        }
        return "reservations";
    }

    @RequestMapping(value="/reservations/approve")
    public String approve(Long id) {
        //setting dates
        LocalDate borrowDate = LocalDate.now();
        LocalDate returnDate = borrowDate.plusDays(7);

        Reservation reservation = reservationService.findById(id).get();

        reservation.setBorrowDate(borrowDate);
        reservation.setReturnDate(returnDate);
        reservation.setStatus("Borrowed");
        reservationService.reserveBook(reservation);

        return "redirect:/reservations";
    }

    @RequestMapping(value="/reservations/deny")
    public String deny(Long id) {
        //setting dates
        LocalDate borrowDate = LocalDate.now();
        LocalDate returnDate = borrowDate.plusDays(7);

        Reservation reservation = reservationService.findById(id).get();
        reservation.setBorrowDate(borrowDate);
        reservation.setReturnDate(returnDate);
        reservation.setStatus("Canceled");
        reservationService.reserveBook(reservation);

        return "redirect:/reservations";
    }

    @RequestMapping(value = "/reservations_member")
    public String myReservations(Model model) {
        List<Reservation> reservationList = reservationService.getAllReservations();
        model.addAttribute("reservations", reservationList);
        return "reservations_member";
    }
}