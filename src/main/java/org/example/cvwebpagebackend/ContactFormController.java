package org.example.cvwebpagebackend;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactFormController {

    @PostMapping("/submit-message")
    public String submitMessage(@RequestParam String name, @RequestParam String email, @RequestParam String message) {
        System.out.println(name + "\n" + email + "\n" + message);
        return "Thank you for your message!";
    }
}
