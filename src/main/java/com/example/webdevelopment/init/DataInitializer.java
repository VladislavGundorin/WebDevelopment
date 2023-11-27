package com.example.webdevelopment.init;

import com.example.webdevelopment.dto.*;
import com.example.webdevelopment.enums.Category;
import com.example.webdevelopment.enums.Engine;
import com.example.webdevelopment.enums.Role;

import com.example.webdevelopment.enums.Transmission;
import com.example.webdevelopment.model.Brand;
import com.example.webdevelopment.model.Model;
import com.example.webdevelopment.model.UserRole;
import com.example.webdevelopment.repositorie.*;
import com.example.webdevelopment.service.*;
import com.example.webdevelopment.views.OfferViewModel;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static com.example.webdevelopment.enums.Role.ADMIN;

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
        userRoleRepository.save(new UserRole(ADMIN));

        List<UserRole> userRoles = userRoleRepository.findAll();
        System.out.println(userRoles.toString());
        List<UserRoleDTO> userRoleDTOS = userRoleService.getAllUserRoles();
        System.out.println(userRoleDTOS.toString());

        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(faker.name().username());
            userDTO.setPassword(faker.internet().password());
            userDTO.setFirstName(faker.name().firstName());
            userDTO.setLastName(faker.name().lastName());
            userDTO.setActive(random.nextBoolean());
            userDTO.setImageUrl(faker.internet().image());
            userDTO.setRole(userRoleDTOS.get(random.nextInt(2)));

            userService.createUser(userDTO);
        }
        List<UserDTO> userDTOList = userService.getAllUsers();
        String[] carBrands = {"BMW", "Audi", "Honda", "Ford", "HAVAL"};

        for (String brandName : carBrands) {
            BrandDTO brandDTO = new BrandDTO(UUID.randomUUID(), brandName);
            brandDTO.setId(UUID.randomUUID());
            brandDTO.setName(brandName);


            Brand brand = new Brand();
            brand.setName(brandDTO.getName());
            brandRepository.save(brand);

            List<ModelDTO> models = new ArrayList<>();
            if (brandName.equals("BMW")) {
                models.add(new ModelDTO(null, "3 Series", Category.CAR, 2018, 2022, "/picture/x3_series.jpg", brandDTO));
                models.add(new ModelDTO(null, "X5", Category.CAR, 2019, 2022, "/picture/x5.jpg", brandDTO));
                models.add(new ModelDTO(null, "M5", Category.CAR, 2019, 2023, "/picture/bmw m5.jpg", brandDTO));
                models.add(new ModelDTO(null, "M1", Category.CAR, 2017, 2021, "/picture/bmw m1.jpg", brandDTO));
                models.add(new ModelDTO(null, "M2", Category.CAR, 2016, 2023, "/picture/m2.jpg", brandDTO));
                models.add(new ModelDTO(null, "X3", Category.CAR, 2015, 2022, "/picture/bmw_x3.jpg", brandDTO));
//                models.add(new ModelDTO(null, "X4", Category.CAR, 2016, 2023, "https://example.com/bmwx4.jpg", brandDTO));
            } else if (brandName.equals("Audi")) {
                models.add(new ModelDTO(null, "A4", Category.CAR, 2015, 2022, "/picture/audi a4.jpeg", brandDTO));
                models.add(new ModelDTO(null, "Q5", Category.CAR, 2016, 2022, "/picture/audi q5.jpg", brandDTO));
                models.add(new ModelDTO(null, "A3", Category.CAR, 2013, 2022, "/picture/audi_a3.jpg", brandDTO));
//                models.add(new ModelDTO(null, "Q8 e-tron", Category.CAR, 2019, 2023, "https://example.com/audiq8etron.jpg", brandDTO));
                models.add(new ModelDTO(null, "RS 6", Category.CAR, 2018, 2023, "/picture/audi_rs6.jpg", brandDTO));
//                models.add(new ModelDTO(null, "TT quattro sport", Category.CAR, 2017, 2022, "https://example.com/auditqquattrosport.jpg", brandDTO));
//                models.add(new ModelDTO(null, "TT RS", Category.CAR, 2016, 2022, "https://example.com/audittRS.jpg", brandDTO));
            } else if (brandName.equals("Honda")) {
                models.add(new ModelDTO(null, "Legend", Category.CAR, 2000, 2022, "/picture/honda legend.jpg", brandDTO));
                models.add(new ModelDTO(null, "Saber", Category.CAR, 1995, 2003, "/picture/honda saber.jpg", brandDTO));
                models.add(new ModelDTO(null, "CR-V", Category.CAR, 1997, 2023, "/picture/honda c-rv.jpg", brandDTO));
//                models.add(new ModelDTO(null, "Life", Category.CAR, 1971, 2022, "https://example.com/hondalife.jpg", brandDTO));
//                models.add(new ModelDTO(null, "S2000", Category.CAR, 1999, 2009, "https://example.com/hondas2000.jpg", brandDTO));
            } else if (brandName.equals("Ford")) {
                models.add(new ModelDTO(null, "C-MAX", Category.CAR, 2012, 2023, "/picture/FordC-MAX.jpg", brandDTO));
                models.add(new ModelDTO(null, "ECOSPORT", Category.CAR, 2013, 2023, "/picture/ford ecospor.jpg", brandDTO));
//                models.add(new ModelDTO(null, "FIESTA", Category.CAR, 2008, 2023, "https://example.com/fordfiesta.jpg", brandDTO));
//                models.add(new ModelDTO(null, "FOCUS RS", Category.CAR, 2016, 2018, "https://example.com/fordfocusrs.jpg", brandDTO));
//                models.add(new ModelDTO(null, "FUSION", Category.CAR, 2005, 2020, "https://example.com/fordfusion.jpg", brandDTO));
            } else if (brandName.equals("HAVAL")) {
                models.add(new ModelDTO(null, "HAVAL M6", Category.CAR, 2019, 2023, "/picture/Haval_H6.jpg", brandDTO));
                models.add(new ModelDTO(null, "HAVAL JOLION", Category.CAR, 2020, 2023, "/picture/haval jolian.jpg", brandDTO));
//                models.add(new ModelDTO(null, "HAVAL DARGO", Category.CAR, 2018, 2022, "https://example.com/havaldargo.jpg", brandDTO));
//                models.add(new ModelDTO(null, "HAVAL F7", Category.CAR, 2017, 2022, "https://example.com/havalf7.jpg", brandDTO));

            }
            for (ModelDTO modelDTO : models) {
                Model model = new Model();
                model.setName(modelDTO.getName());
                model.setCategory(modelDTO.getCategory());
                model.setStartYear(modelDTO.getStartYear());
                model.setEndYear(modelDTO.getEndYear());
                model.setImageUrl(modelDTO.getImageUrl());
                model.setBrand(brand);
                modelRepository.save(model);
            }
        }
        List<ModelDTO> modelDTOList = modelService.getAllModels();

        for (int i = 0; i < 100; i++) {
            OfferDTO offerDTO = new OfferDTO();
            offerDTO.setDescription(faker.lorem().sentence());
            offerDTO.setEngine(Engine.values()[faker.random().nextInt(Engine.values().length)]);
            offerDTO.setMileage(faker.random().nextInt(1000, 100000));
            offerDTO.setPrice(faker.random().nextInt(900000, 5000000));
            offerDTO.setTransmission(Transmission.values()[faker.random().nextInt(Transmission.values().length)]);
            offerDTO.setYear(faker.random().nextInt(2016, 2020));

            // Выберите бренд случайным образом
            BrandDTO brandDTO = new BrandDTO();
            brandDTO.setName(carBrands[random.nextInt(carBrands.length)]);

            // Выберите модели для выбранного бренда
            List<ModelDTO> brandModels = modelService.getModelsByBrandName(brandDTO.getName());

            // Проверка наличия моделей
            if (!brandModels.isEmpty()) {
                ModelDTO modelDTO = brandModels.get(random.nextInt(brandModels.size()));
                offerDTO.setSeller(userDTOList.get(random.nextInt(userDTOList.size())));
                offerDTO.setModel(modelDTO);
                offerDTO.setImageUrl(modelDTO.getImageUrl());  // Установка изображения из модели
                offerService.createOffer(offerDTO);
            }
        }
        List<OfferDTO> offerDTOList = offerService.getAllOffers();
        List<OfferViewModel> offerViewModels = new ArrayList<>();

        for (OfferDTO offerDTO : offerDTOList) {
            BrandDTO brandDTO = offerDTO.getModel().getBrand();
            ModelDTO modelDTO = offerDTO.getModel();

            OfferViewModel offerViewModel = new OfferViewModel(
                    offerDTO.getId(),
                    brandDTO.getName(),
                    modelDTO.getName(),
                    offerDTO.getPrice(),
                    modelDTO.getImageUrl()
            );

            offerViewModels.add(offerViewModel);
        }
    }
    }
//        offerService.getDescriptionsByBrandAndModel("Audi", "TT RS").forEach(System.out::println);
//        modelService.getModelsByBrandAndStartYear("BMW", 2015).forEach(System.out::println);
//        List<Object[]> results = userRepository.findUsersByRole(Role.ADMIN);
//
//        for (Object[] result : results) {
//            String firstName = (String) result[0];
//            String lastName = (String) result[1];
//            String roleName = ((Role) result[2]).name();
//            System.out.println("Имя: " + firstName + ", Фамилия: " + lastName + ", Роль: " + roleName);
//        }
//        List<Object[]> res = brandRepository.findBrandWithLowestMileageAndPrice();
//
//        for (Object[] row : res) {
//            String brandName = (String) row[0];
//            int mileage = (int) row[1];
//            int price = (int) row[2];
//
//            System.out.println("Brand Name: " + brandName);
//            System.out.println("Mileage: " + mileage);
//            System.out.println("Price: " + price);
//
//        }
//        List<Object[]> ress = userRoleRepository.findActiveUsersWithRoles();
//
//        for (Object[] rew : ress) {
//            String userName = (String) rew[0];
//            String password = (String) rew[1];
//            Role roleName = (Role) rew[2];
//
//            System.out.println("User Name: " + userName);
//            System.out.println("Password: " + password);
//            System.out.println("Role Name: " + roleName);
//        }
//        //Offer reade
//        List<OfferDTO> offer = offerService.getAllOffers();
//        if (offer.isEmpty()) {
//            System.out.println("Нет карточек.");
//        } else {
//            System.out.println("Список всех карточек автомобилей:");
//            for (OfferDTO off : offer) {
//                System.out.println("список информации по каждому автомобилю: " + off.getDescription());
//            }
//        }
//        //create
//        OfferDTO newOffer = new OfferDTO();
//        newOffer.setDescription("New Offer Description");
//        OfferDTO createdOffer = offerService.createOffer(newOffer);
//        System.out.println("Создано предложение: " + createdOffer);
//        //update
//        List<OfferDTO> offers = offerService.getAllOffers();
//        if (!offers.isEmpty()) {
//            OfferDTO offerToUpdate = offers.get(0);
//            String originalDescription = offerToUpdate.getDescription();
//            offerToUpdate.setDescription("Updated Offer Description");
//            OfferDTO updatedOffer = offerService.updateOfferByID(offerToUpdate.getId(), offerToUpdate);
//            System.out.println("Original Description: " + originalDescription);
//            System.out.println("Updated Description: " + updatedOffer.getDescription());
//        } else {
//            System.out.println("Нет предложений для обновления.");
//        }
//        //Delete
//        List<OfferDTO> offerss = offerService.getAllOffers();
//        if (offerss.size() >= 2) {
//            OfferDTO offerToDelete = offerss.get(1);
//            UUID offerIdToDelete = offerToDelete.getId();
//            offerService.deleteOfferById(offerIdToDelete);
//            System.out.println("Предложение с идентификатором " + offerIdToDelete + " был удален.");
//        } else {
//            System.out.println("Недостаточно предложений для удаления второго..");
//        }
//// Brand create
//        BrandDTO newBrand = new BrandDTO();
//        newBrand.setName("New Brand Name");
//        BrandDTO createdBrand = brandService.createBrand(newBrand);
//        System.out.println("Создан бренд: " + createdBrand);
//
//// Brand update
//        List<BrandDTO> brands = brandService.getAllBrands();
//        if (!brands.isEmpty()) {
//            BrandDTO brandToUpdate = brands.get(0);
//            String originalName = brandToUpdate.getName();
//            brandToUpdate.setName("Updated Brand Name");
//            BrandDTO updatedBrand = brandService.updateBrandById(brandToUpdate.getId(), brandToUpdate);
//            System.out.println("Original Name: " + originalName);
//            System.out.println("Updated Name: " + updatedBrand.getName());
//        } else {
//            System.out.println("Нет брендов для обновления.");
//        }
//
//// Brand delete
//        List<BrandDTO> brandsList = brandService.getAllBrands();
//        if (brandsList.size() >= 2) {
//            BrandDTO brandToDelete = brandsList.get(1);
//            UUID brandIdToDelete = brandToDelete.getId();
//            brandService.deleteBrandById(brandIdToDelete);
//            System.out.println("Бренд с идентификатором " + brandIdToDelete + " был удален.");
//        } else {
//            System.out.println("Недостаточно брендов для удаления второго.");
//        }
//        List<BrandDTO> brandss = brandService.getAllBrands();
//        if (brandss.isEmpty()) {
//            System.out.println("Нет доступных брендов.");
//        } else {
//            System.out.println("Список всех брендов:");
//            for (BrandDTO brand : brandss) {
//                System.out.println("Название бренда: " + brand.getName());
//            }
//        }
//
//// Model create
//        ModelDTO newModel = new ModelDTO();
//        newModel.setName("New Model Name");
//        ModelDTO createdModel = modelService.createModel(newModel);
//        System.out.println("Создана модель: " + createdModel);
//
//// Model update
//        List<ModelDTO> models = modelService.getAllModels();
//        if (!models.isEmpty()) {
//            ModelDTO modelToUpdate = models.get(0);
//            String originalName = modelToUpdate.getName();
//            modelToUpdate.setName("Updated Model Name");
//            ModelDTO updatedModel = modelService.updateModelById(modelToUpdate.getId(), modelToUpdate);
//            System.out.println("Original Name: " + originalName);
//            System.out.println("Updated Name: " + updatedModel.getName());
//        } else {
//            System.out.println("Нет моделей для обновления.");
//        }
//
//// Model delete
//        List<ModelDTO> modelsList = modelService.getAllModels();
//        if (modelsList.size() >= 2) {
//            ModelDTO modelToDelete = modelsList.get(1);
//            UUID modelIdToDelete = modelToDelete.getId();
//            modelService.deleteModelById(modelIdToDelete);
//            System.out.println("Модель с идентификатором " + modelIdToDelete + " была удалена.");
//        } else {
//            System.out.println("Недостаточно моделей для удаления второй.");
//        }
//        List<ModelDTO> modelss = modelService.getAllModels();
//        if (modelss.isEmpty()) {
//            System.out.println("Нет доступных моделей.");
//        } else {
//            System.out.println("Список всех моделей:");
//            for (ModelDTO model : modelss) {
//                System.out.println("Идентификатор: " + model.getId());
//                System.out.println("Имя модели: " + model.getName());
//            }
//        }
//    }
//}
//
