package ru.job4j.cars.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.model.*;
import ru.job4j.cars.service.api.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Controller
@SessionAttributes(value = {"brand", "namesToCarModels"})
public class AdvertisementController {

    private final CarBrandService brandService;
    private final EngineService engineService;
    private final CarModelService carModelService;

    private final PhotoService photoService;
    private final AdvertisementService advertisementService;

    public AdvertisementController(CarBrandService brandService, EngineService engineService,
                                   CarModelService carModelService, AdvertisementService advertisementService,
                                   PhotoService photoService) {
        this.brandService = brandService;
        this.engineService = engineService;
        this.carModelService = carModelService;
        this.advertisementService = advertisementService;
        this.photoService = photoService;
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
    public String defineCarBodyType(@SessionAttribute("brand") CarBrand brand,
                                    @RequestParam("modelName") String modelName,
                                    @SessionAttribute("namesToCarModels") Map<String, List<CarModel>> namesToCarModels,
                                    Model model,
                                    SessionStatus sessionStatus) {
        model.addAttribute("models", namesToCarModels.get(modelName));
        model.addAttribute("brand", brand);
        model.addAttribute("modelName", modelName);
        sessionStatus.setComplete();
        return "defineCarBodyType";
    }

    @PostMapping("/defineFields")
    public String defineEngine(Model model, @RequestParam("modelId") Long modelId) {
        model.addAttribute("carModel", carModelService.findById(modelId));
        return "defineFields";
    }

    @PostMapping("/createAdvertisement")
    public String createAdvertisement(@ModelAttribute Advertisement advertisement,
                                      @RequestParam("modelId") Long modelId,
                                      @RequestParam("engineId") Long engineId,
                                      @RequestParam(value = "files") MultipartFile[] photos,
                                      HttpSession session) throws IOException {
        setAdvertisementFields(advertisement, modelId, engineId, photos, session);
        advertisementService.add(advertisement);
        return "redirect:/index";
    }

    @GetMapping("/firstPhoto/{id}")
    public ResponseEntity<Resource> firstPhoto(@PathVariable("id") long id) {
        Advertisement advertisement = advertisementService.findById(id);
        byte[] photo = advertisement.getPhotos().get(0).getPhoto();
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(photo.length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(photo));
    }

    @GetMapping("/advertisementDetail/{id}")
    public String advertisementDetail(Model model, @PathVariable("id") long id) {
        model.addAttribute("advertisement", advertisementService.findById(id));
        return "advertisementDetail";
    }

    @GetMapping("/advertisementPhoto/{id}")
    public ResponseEntity<Resource> advertisementPhoto(@PathVariable("id") long id) {
        byte[] photo = photoService.findById(id).getPhoto();
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(photo.length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(photo));
    }

    @GetMapping("/removeAdvertisement/{id}")
    public String removeAdvertisement(@PathVariable("id") long id) {
        advertisementService.delete(id);
        return "redirect:/index";
    }

    @GetMapping("/soldAdvertisement/{id}")
    public String soldAdvertisement(@PathVariable("id") long id) {
        advertisementService.setSoldById(id);
        return "redirect:/index";
    }

    @GetMapping("/editAdvertisement/{id}")
    public String editAdvertisement(Model model, @PathVariable("id") long id) {
        Advertisement advertisement = advertisementService.findById(id);
        model.addAttribute("advertisement", advertisement);
        model.addAttribute("engines", carModelService.findById(advertisement.getCarModel().getId()).getEngines());
        return "editAdvertisement";
    }

    @PostMapping("/saveUpdatedAdvertisement")
    public String saveUpdatedAdvertisement(@ModelAttribute Advertisement advertisement,
                                           @RequestParam("modelId") Long modelId,
                                           @RequestParam("engineId") Long engineId,
                                           @RequestParam(value = "files") MultipartFile[] photos,
                                           HttpSession session,
                                           Model model) throws IOException {
        setAdvertisementFields(advertisement, modelId, engineId, photos, session);
        advertisementService.replaceWithPhotos(advertisement);
        model.addAttribute("advertisement", advertisementService.findById(advertisement.getId()));
        return "advertisementDetail";
    }

    private void setAdvertisementFields(Advertisement advertisement, Long modelId, Long engineId,
                                        MultipartFile[] photos, HttpSession session) throws IOException {
        advertisement.setCarModel(carModelService.findById(modelId));
        advertisement.setUser((User) session.getAttribute("user"));
        advertisement.setCreated(LocalDateTime.now());
        advertisement.setEngine(engineService.findById(engineId));
        for (MultipartFile photo : photos) {
            Photo carPhoto = Photo.of(photo.getBytes());
            advertisement.addPhoto(carPhoto);
        }
    }
}
