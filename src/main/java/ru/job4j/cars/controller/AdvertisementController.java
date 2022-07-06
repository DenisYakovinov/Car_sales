package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.cars.service.api.CarBodyTypeService;
import ru.job4j.cars.service.api.CarBrandService;

@Controller
public class AdvertisementController {

    private final CarBodyTypeService bodyTypeService;
    private final CarBrandService brandService;

    public AdvertisementController(CarBodyTypeService bodyTypeService, CarBrandService brandService) {
        this.bodyTypeService = bodyTypeService;
        this.brandService = brandService;
    }

    @GetMapping("/addAdvertisement")
    public String addAdvertisement(Model model) {
        model.addAttribute("bodyTypes", bodyTypeService.findAll());
        model.addAttribute("carBrands", brandService.findAll());
        return "addAdvertisement";
    }
}
