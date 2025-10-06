package org.example.cvwebpagebackend;

import org.example.cvwebpagebackend.model.Message;
import org.example.cvwebpagebackend.model.MessageDTO;
import org.example.cvwebpagebackend.repository.MessageRepository;
import org.example.cvwebpagebackend.service.MailService;
import org.example.cvwebpagebackend.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class MessageServiceTest {

    @Autowired
    MessageService service;

    @Autowired
    MessageRepository repository;

    @MockitoBean
    MailService mailService;


    @Test
    void shouldStoreMessageInDB() throws Exception {
        MessageDTO messageDTO = new MessageDTO("name", "email", "message");
        service.saveAndNotify(messageDTO);
        List<Message> messages = repository.findAll();
        assertEquals(messages.get(0).getName(), messageDTO.getName());
    }

}