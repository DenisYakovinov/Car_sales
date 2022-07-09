package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.job4j.cars.model.CarBodyType;
import ru.job4j.cars.model.CarBrand;
import ru.job4j.cars.model.CarModel;
import ru.job4j.cars.service.api.CarBodyTypeService;
import ru.job4j.cars.service.api.CarBrandService;
import ru.job4j.cars.service.api.CarModelService;
import ru.job4j.cars.service.api.EngineService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Controller
@SessionAttributes(value = {"brand", "namesToCarModels"})
public class AdvertisementController {

    private final CarBodyTypeService bodyTypeService;
    private final CarBrandService brandService;
    private final EngineService engineService;
    private final CarModelService carModelService;

    public AdvertisementController(CarBodyTypeService bodyTypeService, CarBrandService brandService,
                                   EngineService engineService, CarModelService carModelService) {
        this.bodyTypeService = bodyTypeService;
        this.brandService = brandService;
        this.engineService = engineService;
        this.carModelService = carModelService;
    }

    @GetMapping("/addAdvertisement")
    public String addAdvertisement(Model model) {
        model.addAttribute("bodyTypes", bodyTypeService.findAll());
        model.addAttribute("carBrands", brandService.findAll());
        model.addAttribute("engines", engineService.findAll());
        return "addAdvertisement";
    }

    @GetMapping("/defineCarBrand")
    public String defineCarBrand(Model model) {
        model.addAttribute("brands", brandService.findAll());
        return "defineCarBrand";
    }

    @PostMapping("/defineCarModel")
    public String defineCarModel(Model model, @RequestParam("brand") long brandId,
                                 HttpSession session) {
        model.addAttribute("brand", brandService.findById(brandId));
        Map<String, List<CarModel>> namesToCarModels = carModelService.findAllByBrand(brandId).stream().collect(
                groupingBy(CarModel::getName));
        model.addAttribute("namesToCarModels", namesToCarModels);
        return "defineCarModel";
    }

    @PostMapping("/defineCarBodyType")
    public String defineCarBodyType(@SessionAttribute("brand") CarBrand brand, Model model,
                                    @RequestParam("modelName") String modelName, HttpSession session,
                                    @SessionAttribute("namesToCarModels") Map<String, List<CarModel>> namesToCarModels,
                                    SessionStatus sessionStatus) {
        model.addAttribute("models", namesToCarModels.get(modelName));
        model.addAttribute("brand", brand);
        model.addAttribute("modelName", modelName);
        sessionStatus.setComplete();
        return "defineCarBodyType";
    }

    @PostMapping("/defineEngine")
    public String defineEngine(Model model, @RequestParam("modelId") Long modelId, HttpSession session) {
        model.addAttribute("model", carModelService.findById(modelId));
        return "defineEngine";
    }

    @PostMapping("/createAdvertisement")
    public String createAdvertisement(Model model, ServletRequest request,
                                      ServletResponse response,
                                      @ModelAttribute CarModel carModel,
                                      @RequestParam("releaseDate") String releaseDate,
                                      @RequestParam("engine") int engineId,
                                      @RequestParam("description") String description) {
        //HttpServletRequest req = (HttpServletRequest) request;
        //CarBrand brand = brandService.findById(brandId);
        //CarBodyType type = bodyTypeService.findById(typeId);
        //Engine engine = engineService.findById(engineId);
        //LocalDate release = LocalDate.parse(releaseDate);
        //CarModel car = new CarModel(carModel, engine, brand, type, release);
        return null;
    }
}
