package codegym.controller;

import codegym.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    HttpSession httpSession;

    @ModelAttribute("user")
    public User getUser() {
        User user = new User("toan", "123");
        return user;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        ModelAndView modelAndView = null;

        if (bindingResult.hasFieldErrors()) {
            modelAndView = new ModelAndView("login");

        } else if (user.getEmail().equals("toan98@gmail.com") && user.getPassword().equals("123456789")) {

            httpSession.setAttribute("user", user);

            modelAndView = new ModelAndView("redirect:/home");
        } else {
            modelAndView = new ModelAndView("redirect:/login");
        }
        return modelAndView;
    }
}
