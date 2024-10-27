package net.ziomson.todo_app.repository;

import net.ziomson.todo_app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {

}
