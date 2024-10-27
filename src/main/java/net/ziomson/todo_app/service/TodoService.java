package net.ziomson.todo_app.service;

import lombok.AllArgsConstructor;
import net.ziomson.todo_app.dto.TodoDto;
import net.ziomson.todo_app.exception.ResourceNotFoundException;
import net.ziomson.todo_app.model.Todo;
import net.ziomson.todo_app.repository.ToDoRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoService implements ITodoService {

    private ToDoRepo toDoRepo;
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        Todo todo = modelMapper.map(todoDto, Todo.class);
        Todo savedTodo = toDoRepo.save(todo);

        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);


        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = toDoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id" + id));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> GetAll() {
        List<Todo> todos = toDoRepo.findAll();

        return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todo todo = toDoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id" + id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        Todo updatedTodo = toDoRepo.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);

    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = toDoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id" + id));
        toDoRepo.deleteById(id);

    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = toDoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id" + id));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedDto = toDoRepo.save(todo);

        return modelMapper.map(updatedDto, TodoDto.class);
    }

    @Override
    public TodoDto incompleteTodo(Long id) {
        Todo todo = toDoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id" + id));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedDto = toDoRepo.save(todo);

        return modelMapper.map(updatedDto, TodoDto.class);
    }
}

