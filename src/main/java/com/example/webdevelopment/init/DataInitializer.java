package com.example.webdevelopment.init;

import com.example.webdevelopment.dto.*;
import com.example.webdevelopment.enums.Category;
import com.example.webdevelopment.enums.Engine;
import com.example.webdevelopment.enums.Role;

import com.example.webdevelopment.model.UserRole;
import com.example.webdevelopment.repositorie.*;
import com.example.webdevelopment.service.*;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
    public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final OfferService offerService;
    private final ModelService modelService;
    private final BrandService brandService;

    @Autowired
    public DataInitializer(UserRepository userRepository, UserRoleRepository userRoleRepository, OfferRepository offerRepository, ModelRepository modelRepository, BrandRepository brandRepository, UserService userService, UserRoleService userRoleService, OfferService offerService, ModelService modelService, BrandService brandService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.offerService = offerService;
        this.modelService = modelService;
        this.brandService = brandService;
    }

    @Override
    public void run(String... args) throws Exception {
        userRoleRepository.save(new UserRole(Role.USER));
        userRoleRepository.save(new UserRole(Role.ADMIN));

        List<UserRole> userRoles = userRoleRepository.findAll();
        System.out.println(userRoles.toString());
        List<UserRoleDTO> userRoleDTOS = userRoleService.getAllUserRoles();
        System.out.println(userRoleDTOS.toString());

        Faker faker = new Faker();
        Random random = new Random();

        for (int i=0; i<100; i++){
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(faker.name().username());
            userDTO.setPassword(faker.internet().password());
            userDTO.setFirstName(faker.name().firstName());
            userDTO.setLastName(faker.name().lastName());
            userDTO.setActive(random.nextBoolean());
            userDTO.setRole(Role.values()[random.nextInt(Role.values().length)]);
            userDTO.setImageUrl(faker.internet().image());
            userDTO.setCreated(LocalDateTime.now());
            userDTO.setModified(LocalDateTime.now());

            userService.createUser(userDTO);
        }
        List<BrandDTO> brandDTOS = new ArrayList<>();
        List<ModelDTO> modelDTOS = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            BrandDTO brandDTO = new BrandDTO();
            brandDTO.setName(faker.company().name());
            brandDTO.setCreated(LocalDateTime.now());
            brandDTO.setModified(LocalDateTime.now());
            brandDTOS.add(brandDTO);

            for (int j = 0; j < 50; j++) {
                ModelDTO modelDTO = new ModelDTO();
                modelDTO.setName(faker.company().industry());
                modelDTO.setCategory(Category.values()[random.nextInt(Category.values().length)]);
                modelDTO.setImageUrl(faker.internet().image());
                modelDTO.setStartYear(faker.number().numberBetween(2000, 2023));
                modelDTO.setEndYear(faker.number().numberBetween(modelDTO.getStartYear(), 2023));
                modelDTO.setBrand(brandDTO.getName());
                modelDTO.setCreated(LocalDateTime.now());
                modelDTO.setModified(LocalDateTime.now());
                modelDTOS.add(modelDTO);

                modelService.createModel(modelDTO);
            }
            brandService.createBrand(brandDTO);
        }
        for (int i=0; i<100; i++){
            OfferDTO offerDTO = new OfferDTO();
            offerDTO.setDescription(faker.lorem().sentence());
            offerDTO.setEngine(Engine.values()[faker.random().nextInt(Engine.values().length)]);
            offerDTO.setImageUrl(faker.internet().image());
            offerDTO.setMileage(faker.random().nextInt(1000, 100000));
            offerDTO.setPrice(faker.random().nextInt(900000, 5000000));
            offerDTO.setYear(faker.random().nextInt(2016, 2020));

            ModelDTO randomModel = modelDTOS.get(random.nextInt(modelDTOS.size()));
            offerDTO.setModel(randomModel.getName());
            offerDTO.setSeller(faker.name().name());
            offerDTO.setCreated(LocalDateTime.now());
            offerDTO.setModified(LocalDateTime.now());
            offerService.createOffer(offerDTO);
        }

    }
}

