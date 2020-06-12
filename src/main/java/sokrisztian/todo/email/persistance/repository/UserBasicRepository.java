package sokrisztian.todo.email.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sokrisztian.todo.email.persistance.domain.UserEntity;

public interface UserBasicRepository extends JpaRepository<UserEntity, Integer> {
}
