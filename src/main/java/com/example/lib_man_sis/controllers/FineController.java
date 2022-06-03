package com.example.lib_man_sis.controllers;

import com.example.lib_man_sis.models.Fine;
import com.example.lib_man_sis.repos.ReservationRepository;
import com.example.lib_man_sis.services.FineService;
import com.example.lib_man_sis.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FineController {
    @Autowired
    private FineService fineService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/fines")
    public String listLibrarians(Model model) {
        List<Fine> finesList = fineService.getAllFines();
        model.addAttribute("fines", finesList);
        return "fines";
    }

//    public void createFine() {
//        List<Fine> fineList = fineService.getAllFines();
//        for (int i = 0; i < fineList.size(); i++) {
//            if (reservationService.getAllReservations().get(i).getStatus() == "Overdue") {
//                for (int j = 0; j < reservationService.getAllReservations().size(); j++) {
//                    if (fineList.get(j).getReservationId() != reservationService.getAllReservations().get(j).getReservationId()) {
//                        Fine fine = new Fine();
//                        fine.setReservationId(reservationService.getAllReservations().get(i).getReservationId());
//                        fine.setFee(20f);
//                        fineService.save(fine);
//                    }
//                }
//            }
//        }
//    }

    @RequestMapping(value="/fines/pay")
    public String pay(Long id, RedirectAttributes redirAttrs) {
        Fine fine = fineService.findById(id).get();
        redirAttrs.addFlashAttribute("success", "Fine paid successfully");
        fine.setStatus("paid");
        fineService.save(fine);
        return "redirect:/fines";
    }
}