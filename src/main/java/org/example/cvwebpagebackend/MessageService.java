package org.example.cvwebpagebackend;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MailService mailService;

    public MessageService(MessageRepository messageRepository, MailService mailService) {
        this.messageRepository = messageRepository;
        this.mailService = mailService;
    }

    public String saveAndNotify(String name, String email, String content) {
        Message message = new Message(name, email, content);
        messageRepository.save(message);
        mailService.sendEmailNotification(message);
        return "Thank you for your message!";
    }

}
