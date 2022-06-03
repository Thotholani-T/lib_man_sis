package com.example.lib_man_sis;

import com.example.lib_man_sis.services.BookService;
import com.example.lib_man_sis.services.FineService;
import com.example.lib_man_sis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ApplicationController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private FineService fineService;

    @GetMapping("/index")
    public String goToHome(HttpSession session) {
        ;
        long usersNum = userService.getAllUsers().size();
        long booksNum = bookService.getAllBooks().size();
        long fineNum = fineService.getAllFines().size();

        System.out.println(usersNum);

        session.setAttribute("users",usersNum);
        session.setAttribute("books",booksNum);
        session.setAttribute("fines",fineNum);
        return "index";
    }
    @GetMapping("/member")
    public String goToMemberHome(HttpSession session) {
        long booksNum = bookService.getAllBooks().size();
        session.setAttribute("books",booksNum);
        return "member";
    }
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
    @GetMapping("/error")
    public String handleError() {
        return "error";
    }
//    @GetMapping("/test")
//    public String testPage() {
//        return "test";
//    }
}
