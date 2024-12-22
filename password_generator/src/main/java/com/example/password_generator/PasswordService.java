package com.example.password_generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepository;

    public Password generateAndSavePassword(int length, boolean includeUpper, boolean includeLower, boolean includeDigits, boolean includeSpecial) {
        String password = generatePassword(length, includeUpper, includeLower, includeDigits, includeSpecial);
        return savePassword(password);
    }

    public String generatePassword(int length, boolean includeUpper, boolean includeLower, boolean includeDigits, boolean includeSpecial) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()_+-=[]{}|;':\",.<>?/";

        String allowedChars = "";
        if (includeUpper) allowedChars += upper;
        if (includeLower) allowedChars += lower;
        if (includeDigits) allowedChars += digits;
        if (includeSpecial) allowedChars += special;

        if (allowedChars.isEmpty()) {
            throw new IllegalArgumentException("No character sets selected");
        }

        return new Random().ints(length, 0, allowedChars.length())
                .mapToObj(allowedChars::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    public Password savePassword(String password) {
        return passwordRepository.save(new Password(password));
    }

    public List<Password> getAllPasswords() {
        return passwordRepository.findAll();
    }
}