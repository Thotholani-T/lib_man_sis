package com.example.lib_man_sis.controllers;

import com.example.lib_man_sis.models.Book;
import com.example.lib_man_sis.models.User;
import com.example.lib_man_sis.services.BookService;
import com.example.lib_man_sis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> usersList = userService.getAllUsers();
        model.addAttribute("users", usersList);
        return "users";
    }

    @PostMapping("/users/add")
    public String add(User user, RedirectAttributes redirAttrs) {
        userService.save(user);
        redirAttrs.addFlashAttribute("success", "Operation Successful");
        return "redirect:/users";
    }

    @RequestMapping("users/findById")
    @ResponseBody
    public Optional<User> findById(long id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/users/update", method = { RequestMethod.PUT, RequestMethod.GET })
    public String update(User user, RedirectAttributes redirAttrs) {
        userService.update(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/users/delete", method = { RequestMethod.DELETE, RequestMethod.GET })
    public String delete(long id, RedirectAttributes redirAttrs) {
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/catalogue")
    public String catalogue(Model model) {
        List<Book> booksList = bookService.getAllBooks();
        model.addAttribute("books", booksList);
        return "catalogue";

    }

    @RequestMapping(value="/users/printId")
    public void generateId(HttpServletResponse response,Long id) throws IOException {
        User user = userService.findById(id).get();
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=libId_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        userService.printCertificate(response,user);
    }


//TEST AND TRAIL METHOD...NOT CONFIRMED TO BE WORKING
//    @RequestMapping(value="/login/authenticate")
//    public String authenticate(@ModelAttribute Model model, long libraryId, String password) {
//        User theUser = new User();
//        theUser.setLibraryId(libraryId);
//        theUser.setPassword(password);
//        model.addAttribute("user",theUser);
//
//        if (!theUser.equals(null)) {
//            if (theUser.getPassword() == password) {
//                String role = userService.findById(libraryId).get().getRole();
//                if (role  == "LIB") {
//                    //got to lib sis
//                    return "redirect:/";
//                } else {
//                    //got to mem sis
//                    return "redirect:/catalogues";
//                }
//            }
//            return "redirect:/login";
//        }
//        return "redirect:/login";
//    }
}
