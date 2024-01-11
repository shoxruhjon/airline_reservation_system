package dev.xshoxruh.airline_reservation_system.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;

@Component
public class MailSenderService {
    public static final String EMAIL = "shorturl@info.com";
    private final JavaMailSender javaMailSender;
    private final Configuration configuration;

    public MailSenderService(JavaMailSender javaMailSender, Configuration configuration) {
        this.javaMailSender = javaMailSender;
        this.configuration = configuration;
    }

    @Async
    public void sendFreeMarkerMail(String username) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(username + "@mail.ru");
            mimeMessageHelper.setTo("to@gmail.com");
            mimeMessageHelper.setSubject("Subject From Test Simple Mail");
            Template template = configuration.getTemplate("activate_account.ftlh");
            String token = Base64.getEncoder().encodeToString(username.getBytes());

            Map<String, String> objectModel = Map.of("username", username, "token", token);

            String htmlMailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, objectModel);
            mimeMessageHelper.setText(htmlMailContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void sendActivationMail(Map<String, String> model) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(EMAIL);
            mimeMessageHelper.setTo(model.get("to"));
            mimeMessageHelper.setSubject("Activation Email For SHORT URL");
            FileSystemResource fileSystemResource = new FileSystemResource("D:/Dasturlash/Java/airline_reservation_system/src/main/resources/static/img/logo.png");
            mimeMessageHelper.addInline("logo_id", fileSystemResource);
            Template template = configuration.getTemplate("activation.ftlh");
            String url = "http://localhost:8080/api/auth";
            if(model.get("role").equals("CUSTOMER"))
                url = "http://localhost:8080/api/auth/activate/" + model.get("code");
            if(model.get("role").equals("AGENT"))
                url = "http://localhost:8080/api/auth/agent/activate/" + model.get("code");
            Map<String, String> objectModel = Map.of("url", url);
            String htmlMailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, objectModel);
            mimeMessageHelper.setText(htmlMailContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void sendUpdatedFlightMail(Map<String, String> model) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(EMAIL);
            mimeMessageHelper.setTo(model.get("to"));
            mimeMessageHelper.setSubject("Updated Flight. Check your flight");
            Template template = configuration.getTemplate("update_flight.ftlh");
            Map<String, String> objectModel = Map.of("username", model.get("username"));
            String htmlMailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, objectModel);
            mimeMessageHelper.setText(htmlMailContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void sendNewFlightMail(Map<String, String> model) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(EMAIL);
            mimeMessageHelper.setTo(model.get("to"));
            mimeMessageHelper.setSubject("Add New Flight.");
            Template template = configuration.getTemplate("new_flight.ftlh");
            Map<String, String> objectModel = Map.of("username", model.get("username"));
            String htmlMailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, objectModel);
            mimeMessageHelper.setText(htmlMailContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}