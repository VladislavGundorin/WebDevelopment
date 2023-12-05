package com.example.webdevelopment.controllers.user;
import com.example.webdevelopment.dto.UserDTO;
import com.example.webdevelopment.enums.Role;
import com.example.webdevelopment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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


    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO registeredUser = userService.registerUser(userDTO);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping("/allusers")
    public String getAllUser(Model model){
        List<UserDTO> userDTOs = userService.getAllUsers();
        model.addAttribute("users", userDTOs);
        return "users-all";
    }
}
