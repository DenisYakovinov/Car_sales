package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.cars.model.Advertisement;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.api.AdvertisementService;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    private final AdvertisementService advertisementService;

    public IndexController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Guest");
        }
        session.setAttribute("user", user);
        List<Advertisement> advertisements = advertisementService.findAllWithPhotos();
        model.addAttribute("advertisements", advertisements);
        return "index";
    }
}
