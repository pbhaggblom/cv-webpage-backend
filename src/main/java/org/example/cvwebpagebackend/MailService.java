package org.example.cvwebpagebackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class MailService {

    @Value("${api.key}")
    private String apiKey;

    @Value("${notification.recipient}")
    private String recipient;

    @Value("${template.id}")
    private String templateId;

    public void sendEmailNotification(Message message) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.brevo.com/v3/smtp/email"))
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("api-key", apiKey)
                .method("POST", HttpRequest.BodyPublishers.ofString(
                        "{\"params\":" +
                                "{\"NAME\":\"" + message.getName() + "\"," +
                                "\"EMAIL\":\"" + message.getEmail() + "\"," +
                                "\"CONTENT\":\"" + message.getMessage() + "\"}," +
                                "\"to\":[{\"email\":\"" + recipient + "\"}]," +
                                "\"templateId\":" + templateId + "}"))
                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
