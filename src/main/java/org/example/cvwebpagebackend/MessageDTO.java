package org.example.cvwebpagebackend;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageDTO {

    @NotNull
    private String name;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String message;

    public MessageDTO(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }
}
