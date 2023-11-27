package com.example.webdevelopment.controllers.offer;

import com.example.webdevelopment.dto.OfferDTO;
import com.example.webdevelopment.service.OfferService;
import com.example.webdevelopment.views.OfferViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("offer/create")
    public OfferDTO createOffer(@RequestBody OfferDTO offerDTO) {
        return offerService.createOffer(offerDTO);
    }

    @GetMapping("/alloffers")
    public String getAllOffers(Model model){
        List<OfferDTO> offerDTOs = offerService.getAllOffers();
        model.addAttribute("offers", offerDTOs);
        return "offers-all";
    }
    @GetMapping("/details/{offer-id}")
    public String offerDetails(@PathVariable("offer-id") UUID offerId, Model model) {
        OfferDTO offerDTO = offerService.getOfferById(offerId);
        model.addAttribute("offerDetails", offerDTO);
        return "offers-details";
    }

    @DeleteMapping("/delete/offer/{id}")
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
    @PostMapping("/delete/offers/{id}")
    public String deleteOffer(@PathVariable UUID id) {
        offerService.deleteOfferById(id);
        return "redirect:/offers/alloffers";
    }
}
