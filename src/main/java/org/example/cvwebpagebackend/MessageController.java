package org.example.cvwebpagebackend;

import brevo.ApiException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

    @CrossOrigin
    @PostMapping("/submit-message")
    public ResponseEntity<String> submitMessage(@Valid @RequestBody MessageDTO messageDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        try {
            messageService.saveAndNotify(messageDto);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong, try again!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Thank you for your message!", HttpStatus.OK);
    }
}
