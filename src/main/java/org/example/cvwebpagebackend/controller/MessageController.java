package org.example.cvwebpagebackend.controller;

import org.example.cvwebpagebackend.util.FormValidator;
import org.example.cvwebpagebackend.model.MessageDTO;
import org.example.cvwebpagebackend.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final MessageService messageService;
    private final FormValidator validator;

    public MessageController(MessageService messageService, FormValidator validator) {
        this.messageService = messageService;
        this.validator = validator;
    }

    @CrossOrigin(origins = "https://pontushaggblom.netlify.app")
    @PostMapping("/submit-message")
    public ResponseEntity<String> submitMessage(@RequestBody MessageDTO messageDto) {

        try {
            validator.validateInput(messageDto);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        try {
            messageService.saveAndNotify(messageDto);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong, try again!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Thank you for your message!", HttpStatus.OK);
    }
}
