package org.example.cvwebpagebackend.service;

import org.example.cvwebpagebackend.model.Message;
import org.example.cvwebpagebackend.model.MessageDTO;
import org.example.cvwebpagebackend.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MailService mailService;

    public MessageService(MessageRepository messageRepository, MailService mailService) {
        this.messageRepository = messageRepository;
        this.mailService = mailService;
    }

    public void saveAndNotify(MessageDTO messageDTO) throws Exception {
        Message message = Message.builder()
                .name(messageDTO.getName())
                .email(messageDTO.getEmail())
                .message(messageDTO.getMessage())
                .build();
        messageRepository.save(message);
        mailService.sendEmailNotification(message);
    }

}
