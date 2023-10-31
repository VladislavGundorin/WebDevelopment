package com.example.webdevelopment.service.impl;

import com.example.webdevelopment.dto.OfferDTO;
import com.example.webdevelopment.model.Offer;
import com.example.webdevelopment.repositorie.OfferRepository;
import com.example.webdevelopment.service.OfferService;
import com.example.webdevelopment.validation.ValidationUtil;
import com.example.webdevelopment.views.OfferViewModel;
import com.example.webdevelopment.views.UserViewModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final ModelMapper modelMapper;
    private final OfferRepository offerRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper, OfferRepository offerRepository, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public OfferDTO createOffer(OfferDTO offerDTO) {
        Set<ConstraintViolation<OfferDTO>> violations = validationUtil.violations(offerDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        Offer offer = modelMapper.map(offerDTO, Offer.class);
        Offer saveOffer = offerRepository.save(offer);
        return modelMapper.map(saveOffer, OfferDTO.class);
    }

    @Override
    public List<OfferDTO> getAllOffers() {
        List<Offer> offers = offerRepository.findAll();
        return offers.stream().map(offer -> modelMapper.map(offer, OfferDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OfferDTO> getOfferById(UUID id) {
        Optional<Offer> optionalOffer = offerRepository.findById(id);
        return optionalOffer.map(offer -> modelMapper.map(offer, OfferDTO.class));
    }

    @Override
    public OfferDTO updateOfferByID(UUID id, OfferDTO offerDTO) {
        Set<ConstraintViolation<OfferDTO>> violations = validationUtil.violations(offerDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        Optional<Offer> optionalOffer = offerRepository.findById(id);
        if (optionalOffer.isPresent()) {
            Offer offer = optionalOffer.get();
            offer.setDescription(offerDTO.getDescription());
            offer.setEngine(offerDTO.getEngine());
            offer.setImageUrl(offerDTO.getImageUrl());
            offer.setMileage(offerDTO.getMileage());
            offer.setPrice(offerDTO.getPrice());
            offer.setTransmission(offerDTO.getTransmission());
            offer.setYear(offerDTO.getYear());
            Offer updateSeller = offerRepository.save(offer);
            return modelMapper.map(updateSeller, OfferDTO.class);
        } else {
            return null;
        }
    }
    @Override
    public OfferDTO updateOfferAllFields(UUID id, OfferDTO offerDTO) {
        Optional<Offer> optionalOffer = offerRepository.findById(id);
        if (optionalOffer.isPresent()) {
            Offer offer = optionalOffer.get();
            offer.setDescription(offerDTO.getDescription());
            offer.setEngine(offerDTO.getEngine());
            offer.setImageUrl(offerDTO.getImageUrl());
            offer.setMileage(offerDTO.getMileage());
            offer.setPrice(offerDTO.getPrice());
            offer.setTransmission(offerDTO.getTransmission());
            offer.setYear(offerDTO.getYear());
            Offer updatedOffer = offerRepository.save(offer);
            return modelMapper.map(updatedOffer, OfferDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public void deleteOfferById(UUID id) {
        offerRepository.deleteById(id);
    }

    @Override
    public List<String> getDescriptionsByBrandAndModel(String brandName, String modelName) {
        return offerRepository.findDescriptionsByBrandAndModel(brandName,modelName);
    }

    @Override
    public List<OfferViewModel> getOfferDataForUserView() {
        List<Offer> offers = offerRepository.findAll();
        List<OfferViewModel> offerViewModels = new ArrayList<>();

        for (Offer offerDTO : offers) {
            OfferViewModel viewModel = new OfferViewModel(offerDTO.getImageUrl(), offerDTO.getMileage(), offerDTO.getPrice());
            offerViewModels.add(viewModel);
        }

        return offerViewModels;
    }
}
