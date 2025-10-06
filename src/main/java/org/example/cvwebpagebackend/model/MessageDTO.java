package org.example.cvwebpagebackend.model;

import lombok.Data;

@Data
public class MessageDTO {

    private String name;
    private String email;
    private String message;

    public MessageDTO(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }
}
