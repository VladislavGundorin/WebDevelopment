package com.example.webdevelopment.controllers.Controller;

import org.springframework.ui.Model;
import com.example.webdevelopment.service.OfferService;
import com.example.webdevelopment.views.OfferViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private OfferService offerService;
    @Autowired
    public void setOfferService(OfferService offerService){
        this.offerService = offerService;
    }
    @GetMapping("/")
    public String homePage(Model model){
        List<OfferViewModel> offerViewModels = offerService.getOfferDataForUserView();
        model.addAttribute("offers", offerViewModels);
        return "index";
    }
}
