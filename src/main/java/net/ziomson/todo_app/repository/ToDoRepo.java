package net.ziomson.todo_app.repository;

import net.ziomson.todo_app.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepo extends JpaRepository<Todo, Long>  {
}
