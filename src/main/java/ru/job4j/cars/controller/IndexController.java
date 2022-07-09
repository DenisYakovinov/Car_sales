package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.cars.model.Advertisement;
import ru.job4j.cars.service.api.AdvertisementService;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    private final AdvertisementService advertisementService;

    public IndexController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        List<Advertisement> advertisements = advertisementService.findAll();
        model.addAttribute("advertisements", advertisements);
        return "index";
    }

    @GetMapping("/tester")
    public String postIndex(Model model, ServletResponse response) {
        List<Advertisement> advertisements = advertisementService.findAll();
        model.addAttribute("advertisements", advertisements);

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
