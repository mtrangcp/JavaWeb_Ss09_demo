package com.demo.ss9.controller;

import com.demo.ss9.model.dto.UserLogin;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class HomeController {
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userLogin", new UserLogin());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("userLogin") UserLogin userLogin,
                        HttpSession session, HttpServletResponse response, Model model) {
        if ( userLogin.getUserName().equals("admin") && userLogin.getPassword().equals("1234")) {

            session.setMaxInactiveInterval(60);
            session.setAttribute("userLogin", userLogin);

            // neu tich chon save pass --> cookie luu info login
            // SAVE PASS -> tạo cookie
            if (userLogin.isSavePass()) {

                Cookie usernameCookie = new Cookie("username", userLogin.getUserName());
                Cookie passwordCookie = new Cookie("password", userLogin.getPassword());

                usernameCookie.setMaxAge(60 * 60 * 24); // 1 ngày
                passwordCookie.setMaxAge(60 * 60 * 24);

                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);

            } else {
                // nếu không tick -> xóa cookie
                Cookie usernameCookie = new Cookie("username", null);
                Cookie passwordCookie = new Cookie("password", null);

                usernameCookie.setMaxAge(0);
                passwordCookie.setMaxAge(0);

                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
            }

            return "home";
        }else {
            model.addAttribute("userLogin", userLogin);
            model.addAttribute("message", "Sai username or password wrong");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {
        session.invalidate();
        model.addAttribute("userLogin", new UserLogin());

        return "login";
    }


}
