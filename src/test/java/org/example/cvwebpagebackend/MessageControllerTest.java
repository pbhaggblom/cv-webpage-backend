package org.example.cvwebpagebackend;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

import static org.assertj.core.api.Assertions.assertThat;


@WebMvcTest(MessageController.class)
class MessageControllerTest {

    @MockitoBean
    MessageService messageService;

    @Autowired
    MockMvc mockMvc;

    MockMvcTester mockMvcTester;

    @PostConstruct
    void setUp() {
        mockMvcTester = MockMvcTester.create(mockMvc);
    }

    @Test
    void shouldReturnOk() throws Exception {
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
    void shouldReturnBadRequest() throws Exception {
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
  
}