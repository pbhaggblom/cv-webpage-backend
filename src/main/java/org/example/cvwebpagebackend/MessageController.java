package org.example.cvwebpagebackend;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/submit-message")
    public String submitMessage(@Valid @RequestBody MessageDTO messageDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return "Something's missing";
        }
        return messageService.saveAndNotify(messageDto);
    }
}
