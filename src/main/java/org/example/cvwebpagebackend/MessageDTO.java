package org.example.cvwebpagebackend;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MessageDTO {

    @NotEmpty(message = "Name is required")
    private String name;

    @Email(message = "Enter valid email address")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Message is required")
    private String message;

    public MessageDTO(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }
}
