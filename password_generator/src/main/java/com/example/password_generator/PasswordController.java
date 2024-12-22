package com.example.password_generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/password")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    // Generate password and save it
    @PostMapping("/generate")
    public Password generatePassword(
            @RequestParam int length,
            @RequestParam boolean includeUpper,
            @RequestParam boolean includeLower,
            @RequestParam boolean includeDigits,
            @RequestParam boolean includeSpecial) {

        // Delegate password generation and saving to the service
        return passwordService.generateAndSavePassword(length, includeUpper, includeLower, includeDigits, includeSpecial);
    }

    // Fetch all saved passwords
    @GetMapping("/all")
    public List<Password> getAllPasswords() {
        return passwordService.getAllPasswords();
    }
}