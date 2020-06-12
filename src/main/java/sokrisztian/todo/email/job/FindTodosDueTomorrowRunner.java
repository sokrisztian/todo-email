package sokrisztian.todo.email.job;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import sokrisztian.todo.email.logic.service.FindTodosDueTomorrowService;

@Component
@Order(1)
public class FindTodosDueTomorrowRunner implements CommandLineRunner {

    private final FindTodosDueTomorrowService service;

    public FindTodosDueTomorrowRunner(FindTodosDueTomorrowService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        service.findGroupedByUsers().forEach(
                user -> System.out.println(user + ": " + user.getTodos())
        );
    }

}
