package org.example.cvwebpagebackend.service;

import brevoModel.*;
import org.example.cvwebpagebackend.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import brevo.ApiClient;
import brevo.ApiException;
import brevo.Configuration;
import brevo.auth.*;
import brevoApi.TransactionalEmailsApi;

@Service
public class MailService {

    @Value("${api.key}")
    private String apiKey;

    @Value("${notification.address}")
    private String notificationAddress;

    public void sendEmailNotification(Message message) throws Exception {
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        ApiKeyAuth apiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKeyAuth.setApiKey(apiKey);

        TransactionalEmailsApi apiInstance = new TransactionalEmailsApi();
        SendSmtpEmail sendSmtpEmail = createSendSmtpEmail(message);

        try {
            CreateSmtpEmail result = apiInstance.sendTransacEmail(sendSmtpEmail);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling TransactionalEmailsApi#sendTransacEmail");
            e.printStackTrace();
            throw new Exception("Couldn't send email notification");
        }
    }

    private SendSmtpEmail createSendSmtpEmail(Message message) {
        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();

        SendSmtpEmailSender sender = new SendSmtpEmailSender();
        sender.setEmail(notificationAddress);
        sender.setName("Portfolio webpage");
        sendSmtpEmail.setSender(sender);

        SendSmtpEmailTo recipient = new SendSmtpEmailTo();
        recipient.setEmail(notificationAddress);
        sendSmtpEmail.setTo(List.of(recipient));

        sendSmtpEmail.setSubject("Message from " + message.getName());
        sendSmtpEmail.setHtmlContent("<html><body>" +
                "<p>Name: " + message.getName() + "</p>" +
                "<p>Email: " + message.getEmail() + "</p>" +
                "<p>" + message.getMessage() + "</p></body></html>");

        return sendSmtpEmail;
    }


}
