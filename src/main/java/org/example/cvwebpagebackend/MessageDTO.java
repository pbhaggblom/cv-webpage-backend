package org.example.cvwebpagebackend;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MessageDTO {

    @NotEmpty(message = "Name ca not be empty")
    private String name;

    @Email(message = "Must be valid email address")
    @NotEmpty(message = "Email can not be empty")
    private String email;

    @NotEmpty(message = "Message can not be empty")
    private String message;

    public MessageDTO(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }
}
