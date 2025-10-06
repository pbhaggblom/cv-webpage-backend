package org.example.cvwebpagebackend.util;

import org.example.cvwebpagebackend.model.MessageDTO;
import org.springframework.stereotype.Component;

@Component
public class FormValidator {

    private final int maxNameLength = 20;
    private final int minNameLength = 2;
    private final int maxMessageLength = 200;
    private final int minMessageLength = 2;

    public void validateInput(MessageDTO messageDTO) throws IllegalArgumentException {
        if (!validateName(messageDTO.getName())) {
            throw new IllegalArgumentException("Name is required to be between " + minNameLength + " and " + maxNameLength + " characters");
        }
        if (!validateEmail(messageDTO.getEmail())) {
            throw new IllegalArgumentException("Please provide valid email address");
        }
        if (!validateMessage(messageDTO.getMessage())) {
            throw new IllegalArgumentException("Message is required to be between " + minMessageLength + " and " + maxMessageLength + " characters");
        }
    }

    public boolean validateName(String name) {
        return name != null && name.length() >= minNameLength && name.length() <= maxNameLength;
    }

    public boolean validateEmail(String email) {
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return email.matches(emailRegex);
    }

    public boolean validateMessage(String message) {
        return message != null && message.length() >= minMessageLength && message.length() <= maxMessageLength;
    }
}
