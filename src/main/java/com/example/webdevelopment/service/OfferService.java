package com.example.webdevelopment.service;

import com.example.webdevelopment.dto.OfferDTO;
import com.example.webdevelopment.model.Offer;
import com.example.webdevelopment.model.User;
import com.example.webdevelopment.views.OfferViewModel;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OfferService {
    OfferDTO createOffer (OfferDTO offerDTO);

    List<OfferDTO> getAllOffers();

    OfferDTO getOfferById(UUID id);

    OfferDTO updateOfferByID(UUID id,OfferDTO offerDTO);

    void deleteOfferById(UUID id);

    OfferDTO updateOfferAllFields (UUID id, OfferDTO offerDTO);

    List<String> getDescriptionsByBrandAndModel(String brandName, String modelName);

    List<OfferViewModel> getOfferDataForUserView();

    List<OfferDTO> getAllOffersOrderByViewCountDesc();

    void incrementViewCount(UUID offerId);
}

