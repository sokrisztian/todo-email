package sokrisztian.todo.email.logic.sender;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import sokrisztian.todo.email.logic.mail.SenderHelper;
import sokrisztian.todo.email.logic.service.FindTodosDueTomorrowService;
import sokrisztian.todo.email.persistance.domain.UserEntity;

@Service
public class TomorrowDueTodosNotificationSender {

    private static final String TOMORROW_DUE_TODOS_TEMPLATE = "tomorrowDueTodos.html";
    private static final String TOMORROW_DUE_TODOS_SUBJECT = "Tomorrow due TODOs";

    private static final String CONTEXT_USERNAME_KEY = "username";
    private static final String CONTEXT_TODOS_KEY = "todos";

    private final FindTodosDueTomorrowService findTodosDueTomorrowService;
    private final SenderHelper senderHelper;

    public TomorrowDueTodosNotificationSender(FindTodosDueTomorrowService findTodosDueTomorrowService, SenderHelper senderHelper) {
        this.findTodosDueTomorrowService = findTodosDueTomorrowService;
        this.senderHelper = senderHelper;
    }

    public void sendNotificationMails() {
        findTodosDueTomorrowService.findGroupedByUsers().forEach(this::sendMailToUser);
    }

    private void sendMailToUser(UserEntity user) {
        String mailContent = senderHelper.processTemplate(TOMORROW_DUE_TODOS_TEMPLATE, createMailContext(user));
        senderHelper.sendMail(user.getEmail(), TOMORROW_DUE_TODOS_SUBJECT, mailContent);
    }

    private Context createMailContext(UserEntity user) {
        Context mailContext = new Context();
        mailContext.setVariable(CONTEXT_USERNAME_KEY, user.getUsername());
        mailContext.setVariable(CONTEXT_TODOS_KEY, user.getTodos());
        return mailContext;
    }

}
