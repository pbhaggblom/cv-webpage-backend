package org.example.cvwebpagebackend;

import jakarta.annotation.PostConstruct;
import org.example.cvwebpagebackend.controller.MessageController;
import org.example.cvwebpagebackend.model.MessageDTO;
import org.example.cvwebpagebackend.service.MessageService;
import org.example.cvwebpagebackend.util.FormValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;


@WebMvcTest(MessageController.class)
class MessageControllerTest {

    @MockitoBean
    MessageService messageService;

    @MockitoBean(answers = Answers.CALLS_REAL_METHODS)
    FormValidator validator;

    @Autowired
    MockMvc mockMvc;

    MockMvcTester mockMvcTester;

    @PostConstruct
    void setUp() {
        mockMvcTester = MockMvcTester.create(mockMvc);
    }

    @Test
    void shouldReturnOk() {
        String requestBody = """
             {
             "name": "name",
             "email": "email@email.com",
             "message": "message"
             }
            """;

        MvcTestResult result = mockMvcTester.post()
                .uri("/submit-message")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .exchange();

        assertThat(result)
                .hasStatus(HttpStatus.OK);
    }

    @Test
    void shouldReturnBadRequest() {
        String requestBody1 = """
             {
             "name": "",
             "email": "email@email.com",
             "message": "message"
             }
            """;

        String requestBody2 = """
             {
             "name": "name",
             "email": "email",
             "message": "message"
             }
            """;

        MvcTestResult result1 = mockMvcTester.post()
                .uri("/submit-message")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody1)
                .exchange();

        MvcTestResult result2 = mockMvcTester.post()
                .uri("/submit-message")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody2)
                .exchange();

        assertThat(result1)
                .hasStatus(HttpStatus.BAD_REQUEST);

        assertThat(result2)
                .hasStatus(HttpStatus.BAD_REQUEST);

    }

    @Test
    void shouldReturnInternalServerError() throws Exception{
        MessageDTO testMessage = new MessageDTO("name", "email@email.com", "message");

        String requestBody = """
             {
             "name": "name",
             "email": "email@email.com",
             "message": "message"
             }
            """;

        doThrow(new Exception()).when(messageService).saveAndNotify(testMessage);

        MvcTestResult result = mockMvcTester.post()
                .uri("/submit-message")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .exchange();

        assertThat(result)
                .hasStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }