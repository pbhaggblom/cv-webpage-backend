package org.example.cvwebpagebackend;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormValidatorTest {

    @Test
    void shouldThrowIllegalArgument() {
        FormValidator validator = new FormValidator();

        MessageDTO invalidNameDTO = new MessageDTO("", "email@email.com", "message");
        MessageDTO invalidEmailDTO = new MessageDTO("name", "email.com", "message");
        MessageDTO invalidMessageDTO = new MessageDTO("name", "email@email.com", "");

        assertThrows(IllegalArgumentException.class, () -> validator.validateInput(invalidNameDTO));
        assertThrows(IllegalArgumentException.class, () -> validator.validateInput(invalidEmailDTO));
        assertThrows(IllegalArgumentException.class, () -> validator.validateInput(invalidMessageDTO));

    }

    @Test
    void shouldPassValidation() {
        FormValidator validator = new FormValidator();

        String validName = "name";
        String validEmail = "name@email.com";
        String validMessage = "message";

        assertTrue(validator.validateName(validName));
        assertTrue(validator.validateEmail(validEmail));
        assertTrue(validator.validateMessage(validMessage));
    }

}