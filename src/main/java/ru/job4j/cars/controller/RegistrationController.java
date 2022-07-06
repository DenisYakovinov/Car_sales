package ru.job4j.cars.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.api.UserService;

@Controller
@ThreadSafe
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, @RequestParam(name = "repeatPassword") String repeatPassword) {
        if (!user.getPassword().equals(repeatPassword)) {
            return "redirect:/registration?failPass=true";
        }
        userService.add(user);
        return "redirect:/loginPage";
    }

    @GetMapping("/registration")
    public String registration(Model model, @RequestParam(name = "fail", required = false) Boolean fail,
                               @RequestParam(name = "failPass", required = false) Boolean failPass) {
        model.addAttribute("fail", fail != null);
        model.addAttribute("failPass", failPass != null);
        return "/registration";
    }
}
