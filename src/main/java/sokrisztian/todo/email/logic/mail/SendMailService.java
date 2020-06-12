package sokrisztian.todo.email.logic.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendMailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendMailService.class);

    private static final String ENCODING_NAME = "UTF-8";

    private final JavaMailSender mailSender;

    @Value("${app.mail.from}")
    private String mailFrom;

    public SendMailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String mailTo, String subject, String mailContent) {
        try {
            MimeMessage messageToSend = createMimeMessageWithAttributes(mailFrom, mailTo, subject, mailContent);
            mailSender.send(messageToSend);
            LOGGER.info("Sent mail with the following attributes: mailFrom:{}, mailTo:{}, subject:{}.", mailFrom, mailTo, subject);
        } catch (MailException e) {
            LOGGER.error("Failed to send mail with the following attributes: mailFrom:{}, mailTo:{}, subject:{}. Exception message: {}", mailFrom, mailTo, subject, e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("Failed to set mail attributes. Exception message: {}", e.getMessage());
        } catch (MessagingException e) {
            LOGGER.error("Failed to set mail attributes: mailFrom:{}, mailTo:{}, subject:{}. Exception message: {}", mailFrom, mailTo, subject, e.getMessage());
        }
    }

    private MimeMessage createMimeMessageWithAttributes(String mailFrom, String mailTo, String subject, String mailContent) throws MessagingException {
        var messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true, ENCODING_NAME);
        messageHelper.setFrom(mailFrom);
        messageHelper.setTo(mailTo);
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        return messageHelper.getMimeMessage();
    }

}
