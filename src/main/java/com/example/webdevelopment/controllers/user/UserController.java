package com.example.webdevelopment.controllers.user;
import com.example.webdevelopment.dto.UserDTO;
import com.example.webdevelopment.enums.Role;
import com.example.webdevelopment.model.Offer;
import com.example.webdevelopment.model.User;
import com.example.webdevelopment.service.OfferService;
import com.example.webdevelopment.service.UserService;
import com.example.webdevelopment.service.impl.AuthService;
import com.example.webdevelopment.views.Profile;
import com.example.webdevelopment.views.UserRegistrationViewModel;
import com.example.webdevelopment.views.UserViewModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final AuthService authService;
    private final OfferService offerService;

    @Autowired
    public UserController(UserService userService, AuthService authService, OfferService offerService) {
        this.userService = userService;
        this.authService = authService;
        this.offerService = offerService;
    }

    @GetMapping("/getuser/{id}")
    public UserDTO getUserById(@PathVariable UUID id) {
        return userService.getUserById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable UUID id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/byRole")
    public List<Object[]> getUsersByRole(@RequestParam Role role) {
        return userService.getUsersByRole(role);
    }

    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping("/allusers")
    public String getAllUser(Model model) {
        List<UserDTO> userDTOs = userService.getAllUsers();
        model.addAttribute("users", userDTOs);
        return "users-all";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("badCredentials", true);

        return "redirect:/users/login";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegistrationViewModel userRegistrationViewModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("regUserView", userRegistrationViewModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.regUserView", bindingResult);
            return "redirect:/users/register";
        }

        authService.registerUser(userRegistrationViewModel);
        authService.authWithHttpServletRequest(request, userRegistrationViewModel.getUsername(), userRegistrationViewModel.getPassword());

        return "redirect:/";
    }

    @ModelAttribute("regUserView")
    public UserRegistrationViewModel initUser() {
        return new UserRegistrationViewModel();
    }

    @GetMapping("/register")
    public String regNewUSer() {
        return "register";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String username = principal.getName();
        User user = userService.getUserByUsername(username);

        Profile userProfileView = new Profile(
                username,
                user.getFirstName(),
                user.getLastName(),
                user.getPassword()
        );
        model.addAttribute("offers",userService.allUserOffers(username));
        model.addAttribute("user", userProfileView);

        return "profile";
    }

}
