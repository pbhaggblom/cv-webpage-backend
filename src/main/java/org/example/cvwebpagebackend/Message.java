package org.example.cvwebpagebackend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String message;

    public Message(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public Message() {}
}
