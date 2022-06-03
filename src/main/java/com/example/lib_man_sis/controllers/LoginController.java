package com.example.lib_man_sis.controllers;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.lib_man_sis.models.User;
import com.example.lib_man_sis.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    @Autowired
    private LoginService userService;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/login/authenticate")
    public String login(@ModelAttribute("user") User user, RedirectAttributes redirAttrs, HttpSession session) {
        User oauthUser = userService.login(user.getLibraryId(), user.getPassword());

        System.out.println(oauthUser);
        if(Objects.nonNull(oauthUser))
        {
            System.out.println(oauthUser.getRole());
            System.out.println(oauthUser.getRole().equals("LIB"));
            session.setAttribute("firstName",oauthUser.getFirstName());
            session.setAttribute("role",oauthUser.getRole());

            redirAttrs.addFlashAttribute("success", "Welcome to your account " + oauthUser.getFirstName());

            if (oauthUser.getRole().equals("LIB")) {
                return "redirect:/index";
            } else if (oauthUser.getRole().equals("MEM")){
                return "redirect:/member";
            } else {
                return "redirect:login";
            }
        } else {
            redirAttrs.addFlashAttribute("error", "Incorrect Library ID or Password");
            return "redirect:/login";
        }
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logoutDo(HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirAttrs, HttpSession session)
    {
        session.removeAttribute("firstName");
        session.removeAttribute("role");
        session.removeAttribute("users");
        session.removeAttribute("books");
        session.removeAttribute("fines");
        redirAttrs.addFlashAttribute("success", "Successfully Logged Out");
        return "redirect:/login";
    }

}
