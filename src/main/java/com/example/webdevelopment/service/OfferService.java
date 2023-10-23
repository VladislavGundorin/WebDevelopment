package com.example.webdevelopment.service;

import com.example.webdevelopment.dto.OfferDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OfferService {
    OfferDTO createOffer (OfferDTO offerDTO);

    List<OfferDTO> getAllOffers();

    Optional<OfferDTO> getOfferById(UUID id);

    OfferDTO updateOfferByID(UUID id,OfferDTO offerDTO);

    void deleteOfferById(UUID id);

    List<OfferDTO> findDescriptionsByModelName(String modelName);

}
