package net.ziomson.todo_app.service;

import net.ziomson.todo_app.dto.TodoDto;

import java.util.List;

public interface ITodoService {

    TodoDto addTodo(TodoDto todoDto);


    TodoDto getTodo(Long id);

List<TodoDto> GetAll();

TodoDto updateTodo(TodoDto todoDto, Long id);

void deleteTodo(Long id);

TodoDto completeTodo(Long id);



    TodoDto incompleteTodo(Long id);
}
