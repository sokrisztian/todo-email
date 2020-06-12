package sokrisztian.todo.email.job;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import sokrisztian.todo.email.logic.sender.TomorrowDueTodosNotificationSender;

@Component
@Order(1)
public class FindTodosDueTomorrowRunner implements CommandLineRunner {

    private final TomorrowDueTodosNotificationSender sender;

    public FindTodosDueTomorrowRunner(TomorrowDueTodosNotificationSender sender) {
        this.sender = sender;
    }

    @Override
    public void run(String... args) {
        sender.sendNotificationMails();
    }

}
