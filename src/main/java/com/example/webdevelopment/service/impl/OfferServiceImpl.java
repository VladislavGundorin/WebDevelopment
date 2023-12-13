package com.example.webdevelopment.service.impl;

import com.example.webdevelopment.dto.BrandDTO;
import com.example.webdevelopment.dto.ModelDTO;
import com.example.webdevelopment.dto.OfferDTO;
import com.example.webdevelopment.dto.UserDTO;
import com.example.webdevelopment.model.Brand;
import com.example.webdevelopment.model.Model;
import com.example.webdevelopment.model.Offer;
import com.example.webdevelopment.model.User;
import com.example.webdevelopment.repositorie.BrandRepository;
import com.example.webdevelopment.repositorie.ModelRepository;
import com.example.webdevelopment.repositorie.OfferRepository;
import com.example.webdevelopment.repositorie.UserRepository;
import com.example.webdevelopment.service.OfferService;
import com.example.webdevelopment.validation.ValidationUtil;
import com.example.webdevelopment.views.OfferViewModel;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class OfferServiceImpl implements OfferService {
    private final ModelMapper modelMapper;
    private final OfferRepository offerRepository;
    private final ValidationUtil validationUtil;
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final UserRepository userRepository;

    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper, OfferRepository offerRepository, ValidationUtil validationUtil,ModelRepository modelRepository, BrandRepository brandRepository,UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
        this.validationUtil = validationUtil;
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
    }
    @Override
    @Transactional
    @CacheEvict(value = "offerCache", allEntries = true)
    public OfferDTO createOffer(OfferDTO offerDTO) {
        Set<ConstraintViolation<OfferDTO>> violations = validationUtil.violations(offerDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        UserDTO sellerDTO = offerDTO.getSeller();
        List<User> existingSellers = userRepository.findByFirstNameAndLastName(sellerDTO.getFirstName(), sellerDTO.getLastName());
        User seller;
        if (existingSellers.isEmpty()) {
            seller = modelMapper.map(sellerDTO, User.class);
            seller = userRepository.save(seller);
        } else {
            seller = existingSellers.get(0);
        }
        BrandDTO brandDTO = offerDTO.getModel().getBrand();
        Brand brand;
        List<Brand> existingBrands = brandRepository.findByName(brandDTO.getName());
        if (!existingBrands.isEmpty()) {
            brand = existingBrands.get(0);
        } else {
            brand = brandRepository.save(modelMapper.map(brandDTO, Brand.class));
        }
        ModelDTO modelDTO = offerDTO.getModel();
        String modelName = modelDTO.getName();
        List<Model> existingModels = modelRepository.findByBrandNameAndModelName(brandDTO.getName(), modelName);
        Model model;
        if (existingModels.isEmpty()) {
            model = modelMapper.map(modelDTO, Model.class);
            model.setBrand(brand);

            model = modelRepository.save(model);
        } else {
            model = existingModels.get(0);
        }

        Offer offer = modelMapper.map(offerDTO, Offer.class);
        offer.setModel(model);
        offer.setSeller(seller);

        Offer savedOffer = offerRepository.save(offer);

        return modelMapper.map(savedOffer, OfferDTO.class);
    }

    @Override
    @Cacheable(value = "offerCache")
    public List<OfferDTO> getAllOffers() {
        List<Offer> offers = offerRepository.findAll();
        return offers.stream().map(offer -> modelMapper.map(offer, OfferDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    @Cacheable(value = "offerCache", key = "#id")
    public OfferDTO getOfferById(UUID id) {
        Optional<Offer> offerOptional = offerRepository.findById(id);
        if (offerOptional.isPresent()) {
            return modelMapper.map(offerOptional.get(), OfferDTO.class);
        }
        return null;
    }
    @Override
    @CacheEvict(value = "offerCache", key = "#id")
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
    @CacheEvict(value = "offerCache", key = "#id")
    public void deleteOfferById(UUID id) {
        offerRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "offerCache", key = "#brandName + '-' + #modelName")
    public List<String> getDescriptionsByBrandAndModel(String brandName, String modelName) {
        return offerRepository.findDescriptionsByBrandAndModel(brandName,modelName);
    }
    @Override
    @Cacheable(value = "offerCache", key = "'offerDataForUserView'")
    public List<OfferViewModel> getOfferDataForUserView() {
        List<Offer> offers = offerRepository.findAll();
        List<OfferViewModel> offerViewModels = new ArrayList<>();

        for (Offer offer : offers) {
            Brand brand = offer.getModel().getBrand();
            Model model = offer.getModel();

            OfferViewModel viewModel = new OfferViewModel(

                    offer.getId(),
                    brand.getName(),
                    model.getName(),
                    offer.getPrice(),
                    model.getImageUrl()
            );

            offerViewModels.add(viewModel);
        }
        return offerViewModels;
    }
    @Override
    public List<OfferDTO> getAllOffersOrderByViewCountDesc() {
        List<Offer> offers = offerRepository.findAllByOrderByViewCountDesc();
        return offers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private OfferDTO convertToDTO(Offer offer) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(offer, OfferDTO.class);
    }
    @Override
    public void incrementViewCount(UUID offerId) {
        Offer offer = offerRepository.findById(offerId).orElse(null);
        if (offer != null) {
            offer.setViewCount(offer.getViewCount() + 1);
            offerRepository.save(offer);
        }
    }
}
