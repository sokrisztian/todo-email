package sokrisztian.todo.email.logic.mail;

import org.springframework.stereotype.Component;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class SenderHelper {

    private final ITemplateEngine templateEngine;
    private final SendMailService sendMailService;

    public SenderHelper(ITemplateEngine templateEngine, SendMailService sendMailService) {
        this.templateEngine = templateEngine;
        this.sendMailService = sendMailService;
    }

    public String processTemplate(String template, Context mailContext) {
        return templateEngine.process(template, mailContext);
    }

    public void sendMail(String mailTo, String subject, String mailContent) {
        sendMailService.sendMail(mailTo, subject, mailContent);
    }

}
