package org.example.cvwebpagebackend;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public String saveMessage(String name, String email, String message) {
        messageRepository.save(new Message(name, email, message));
        return "Thank you for your message!";
    }
}
