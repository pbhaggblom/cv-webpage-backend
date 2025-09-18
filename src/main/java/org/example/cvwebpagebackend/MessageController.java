package org.example.cvwebpagebackend;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/submit-message")
    public String submitMessage(@RequestParam String name, @RequestParam String email, @RequestParam String message) {
        System.out.println(name + "\n" + email + "\n" + message);
        return messageService.saveMessage(name, email, message);
    }
}
