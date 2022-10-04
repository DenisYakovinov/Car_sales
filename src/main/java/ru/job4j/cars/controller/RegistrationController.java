package ru.job4j.cars.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cars.exception.EmailReservedException;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.api.UserService;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, @RequestParam(name = "repeatPassword") String repeatPassword) {
        userService.add(user);
        return "redirect:/loginPage";
    }

    @GetMapping("/registration")
    public String registration(Model model, @RequestParam(name = "fail", required = false) Boolean fail,
                               @RequestParam(name = "failPass", required = false) Boolean failPass) {
        model.addAttribute("fail", fail != null);
        model.addAttribute("failPass", failPass != null);
        return "registration";
    }

    @ExceptionHandler(EmailReservedException.class)
    public String handleException(Exception e) {
        return "redirect:/registration?fail=true";
    }
}
