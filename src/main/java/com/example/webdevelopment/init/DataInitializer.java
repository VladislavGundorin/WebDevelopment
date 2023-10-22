package com.example.webdevelopment.init;

import com.example.webdevelopment.dto.*;
import com.example.webdevelopment.enums.Category;
import com.example.webdevelopment.enums.Engine;
import com.example.webdevelopment.enums.Role;

import com.example.webdevelopment.model.Brand;
import com.example.webdevelopment.model.Model;
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
import java.util.UUID;

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
        String[] carBrands = {"BMW","Audi","Honda","Ford","HAVAL"};

        for (String brandName : carBrands) {
            BrandDTO brandDTO = new BrandDTO();
            brandDTO.setId(UUID.randomUUID());
            brandDTO.setName(brandName);
            brandDTO.setCreated(LocalDateTime.now());
            brandDTO.setModified(LocalDateTime.now());

            Brand brand = new Brand();
            brand.setName(brandDTO.getName());
            brand.setCreated(brandDTO.getCreated());
            brand.setModified(brandDTO.getModified());
            brandRepository.save(brand);

            List<ModelDTO> models = new ArrayList<>();
            if (brandName.equals("BMW")) {
                models.add(new ModelDTO(null, "3 Series", Category.CAR, 2018, 2022, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/bmw3series.jpg", brandName));
                models.add(new ModelDTO(null, "X5", Category.CAR, 2019, 2022, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/bmwx5.jpg", brandName));
                models.add(new ModelDTO(null, "M5", Category.CAR, 2019, 2023, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/bmwm5.jpg", brandName));
                models.add(new ModelDTO(null, "M1", Category.CAR, 2017, 2021, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/bmwm1.jpg", brandName));
                models.add(new ModelDTO(null, "M2", Category.CAR, 2016, 2023, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/bmwm2.jpg", brandName));
                models.add(new ModelDTO(null, "X3", Category.CAR, 2015, 2022, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/bmwx3.jpg", brandName));
                models.add(new ModelDTO(null, "X4", Category.CAR, 2016, 2023, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/bmwx4.jpg", brandName));
            } else if (brandName.equals("Audi")) {
                models.add(new ModelDTO(null, "A4", Category.CAR, 2015, 2022, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/audia4.jpg", brandName));
                models.add(new ModelDTO(null, "Q5", Category.CAR, 2016, 2022, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/audiq5.jpg", brandName));
                models.add(new ModelDTO(null, "A3", Category.CAR, 2013, 2022, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/audia3.jpg", brandName));
                models.add(new ModelDTO(null, "Q8 e-tron", Category.CAR, 2019, 2023, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/audiq8etron.jpg", brandName));
                models.add(new ModelDTO(null, "A6", Category.CAR, 2018, 2023, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/audia6.jpg", brandName));
                models.add(new ModelDTO(null, "TT quattro sport", Category.CAR, 2017, 2022, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/auditqquattrosport.jpg", brandName));
                models.add(new ModelDTO(null, "TT RS", Category.CAR, 2016, 2022, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/audittRS.jpg", brandName));
            } else if (brandName.equals("Honda")) {
                models.add(new ModelDTO(null, "Legend", Category.CAR, 2000, 2022, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/hondalegend.jpg", brandName));
                models.add(new ModelDTO(null, "Saber", Category.CAR, 1995, 2003, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/hondasaber.jpg", brandName));
                models.add(new ModelDTO(null, "CR-V", Category.CAR, 1997, 2023, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/hondacrv.jpg", brandName));
                models.add(new ModelDTO(null, "Life", Category.CAR, 1971, 2022, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/hondalife.jpg", brandName));
                models.add(new ModelDTO(null, "S2000", Category.CAR, 1999, 2009, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/hondas2000.jpg", brandName));
            } else if (brandName.equals("Ford")) {
                models.add(new ModelDTO(null, "C-MAX", Category.CAR, 2012, 2023, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/fordcmax.jpg", brandName));
                models.add(new ModelDTO(null, "ECOSPORT", Category.CAR, 2013, 2023, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/fordecosport.jpg", brandName));
                models.add(new ModelDTO(null, "FIESTA", Category.CAR, 2008, 2023, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/fordfiesta.jpg", brandName));
                models.add(new ModelDTO(null, "FOCUS RS", Category.CAR, 2016, 2018, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/fordfocusrs.jpg", brandName));
                models.add(new ModelDTO(null, "FUSION", Category.CAR, 2005, 2020, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/fordfusion.jpg", brandName));
            } else if (brandName.equals("HAVAL")) {
            models.add(new ModelDTO(null, "HAVAL M6", Category.CAR, 2019, 2023, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/havalm6.jpg", brandName));
            models.add(new ModelDTO(null, "HAVAL JOLION", Category.CAR, 2020, 2023, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/havaljolion.jpg", brandName));
            models.add(new ModelDTO(null, "HAVAL DARGO", Category.CAR, 2018, 2022, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/havaldargo.jpg", brandName));
            models.add(new ModelDTO(null, "HAVAL F7", Category.CAR, 2017, 2022, LocalDateTime.now(), LocalDateTime.now(), "https://example.com/havalf7.jpg", brandName));

        }
            for (ModelDTO modelDTO : models) {
                Model model = new Model();
                model.setName(modelDTO.getName());
                model.setCategory(modelDTO.getCategory());
                model.setStartYear(modelDTO.getStartYear());
                model.setEndYear(modelDTO.getEndYear());
                model.setCreated(modelDTO.getCreated());
                model.setModified(modelDTO.getModified());
                model.setImageUrl(modelDTO.getImageUrl());

                modelRepository.save(model);
            }
        }

//        for (int i = 0; i < 5; i++) {
//            BrandDTO brandDTO = new BrandDTO();
//            brandDTO.setName(faker.company().name());
//            brandDTO.setCreated(LocalDateTime.now());
//            brandDTO.setModified(LocalDateTime.now());
//            brandDTOS.add(brandDTO);
//
//            for (int j = 0; j < 50; j++) {
//                ModelDTO modelDTO = new ModelDTO();
//                modelDTO.setName(faker.company().industry());
//                modelDTO.setCategory(Category.values()[random.nextInt(Category.values().length)]);
//                modelDTO.setImageUrl(faker.internet().image());
//                modelDTO.setStartYear(faker.number().numberBetween(2000, 2023));
//                modelDTO.setEndYear(faker.number().numberBetween(modelDTO.getStartYear(), 2023));
//                modelDTO.setBrand(brandDTO.getName());
//                modelDTO.setCreated(LocalDateTime.now());
//                modelDTO.setModified(LocalDateTime.now());
//                modelDTOS.add(modelDTO);
//
//                modelService.createModel(modelDTO);
//            }
//            brandService.createBrand(brandDTO);
//        }
        for (int i=0; i<100; i++){
            OfferDTO offerDTO = new OfferDTO();
            offerDTO.setDescription(faker.lorem().sentence());
            offerDTO.setEngine(Engine.values()[faker.random().nextInt(Engine.values().length)]);
            offerDTO.setImageUrl(faker.internet().image());
            offerDTO.setMileage(faker.random().nextInt(1000, 100000));
            offerDTO.setPrice(faker.random().nextInt(900000, 5000000));
            offerDTO.setYear(faker.random().nextInt(2016, 2020));

            List<Model> allModels = modelRepository.findAll();
            Model randomModel = allModels.get(random.nextInt(allModels.size()));
            offerDTO.setModel(randomModel.getName());
            offerDTO.setSeller(faker.name().name());
            offerDTO.setCreated(LocalDateTime.now());
            offerDTO.setModified(LocalDateTime.now());
            offerService.createOffer(offerDTO);
        }

    }
}

