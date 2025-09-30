package org.example.cvwebpagebackend;

import brevo.ApiException;
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
