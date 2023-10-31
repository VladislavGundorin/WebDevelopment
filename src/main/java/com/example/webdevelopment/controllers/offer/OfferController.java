package com.example.webdevelopment.controllers.offer;

import com.example.webdevelopment.dto.OfferDTO;
import com.example.webdevelopment.service.OfferService;
import com.example.webdevelopment.views.OfferViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/create")
    public OfferDTO createOffer(@RequestBody OfferDTO offerDTO) {
        return offerService.createOffer(offerDTO);
    }

    @GetMapping("/alloffers")
    public List<OfferDTO> getAllOffers() {
        return offerService.getAllOffers();
    }

    @GetMapping("/{id}")
    public OfferDTO getOfferById(@PathVariable UUID id) {
        return offerService.getOfferById(id).orElse(null);
    }


    @DeleteMapping("/{id}")
    public void deleteOfferById(@PathVariable UUID id) {
        offerService.deleteOfferById(id);
    }

    @GetMapping("/descriptions")
    public List<String> getDescriptionsByBrandAndModel(
            @RequestParam(name = "brandname") String brandName,
            @RequestParam(name = "modelname") String modelName) {
        return offerService.getDescriptionsByBrandAndModel(brandName, modelName);
    }
    @GetMapping("/offer-view")
    public List<OfferViewModel> getOfferDataForUserView() {
        return offerService.getOfferDataForUserView();
    }
}
