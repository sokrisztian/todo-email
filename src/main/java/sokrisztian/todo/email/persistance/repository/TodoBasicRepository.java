package sokrisztian.todo.email.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sokrisztian.todo.email.persistance.domain.TodoEntity;

public interface TodoBasicRepository extends JpaRepository<TodoEntity, Integer> {
}
