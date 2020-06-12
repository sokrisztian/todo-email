package sokrisztian.todo.email.logic.service;

import org.springframework.stereotype.Service;
import sokrisztian.todo.email.persistance.domain.TodoEntity;
import sokrisztian.todo.email.persistance.domain.UserEntity;
import sokrisztian.todo.email.persistance.repository.UserBasicRepository;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindTodosDueTomorrowService {

    private final UserBasicRepository userRepository;
    private final Clock clock;

    public FindTodosDueTomorrowService(UserBasicRepository userRepository, Clock clock) {
        this.userRepository = userRepository;
        this.clock = clock;
    }

    public List<UserEntity> findGroupedByUsers() {
        LocalDate tomorrow = LocalDate.now(clock).plusDays(1);
        List<UserEntity> users = userRepository.findAll();

        return collectTodosGroupedByUsers(users, tomorrow);
    }

    private List<UserEntity> collectTodosGroupedByUsers(List<UserEntity> users, LocalDate todoDeadlineDate) {
        users.forEach(user -> filterUserTodosByDeadlineDate(user, todoDeadlineDate));
        return users.stream()
                .filter(user -> !user.getTodos().isEmpty())
                .collect(Collectors.toList());
    }

    private void filterUserTodosByDeadlineDate(UserEntity user, LocalDate todoDeadlineDate) {
        user.setTodos(filterTodosByDeadlineDate(user.getTodos(), todoDeadlineDate));
    }

    private List<TodoEntity> filterTodosByDeadlineDate(List<TodoEntity> todos, LocalDate deadlineDate) {
        return todos.stream()
                .filter(todo -> atDate(todo.getDeadline(), deadlineDate))
                .collect(Collectors.toList());
    }

    private boolean atDate(LocalDateTime deadline, LocalDate givenDeadlineDate) {
        return deadline != null && deadline.toLocalDate().equals(givenDeadlineDate);
    }

}
