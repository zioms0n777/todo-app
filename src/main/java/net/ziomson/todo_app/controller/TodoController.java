package net.ziomson.todo_app.controller;


import lombok.AllArgsConstructor;
import net.ziomson.todo_app.dto.TodoDto;
import net.ziomson.todo_app.model.Todo;
import net.ziomson.todo_app.service.TodoService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos/")
@AllArgsConstructor

public class TodoController {

    private TodoService todoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("add")
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto)
    {
        TodoDto savedTodo= todoService.addTodo(todoDto);


        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId)
    {
        TodoDto todoDto= todoService.getTodo(todoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("all")
    public ResponseEntity<List<TodoDto>> getAllTodos()
    {
        List<TodoDto> all =todoService.GetAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("update")
    public ResponseEntity<TodoDto> updateTodo(@RequestParam Long id, @RequestBody TodoDto todoDto)
    {
        TodoDto todo= todoService.updateTodo(todoDto, id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id)
    {
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo deleted sucessfully!.");
     }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
     @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId)
     {
         TodoDto todoDto = todoService.completeTodo(todoId);
         return new ResponseEntity<>(todoDto, HttpStatus.OK);
     }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long todoId)
    {
        TodoDto todoDto = todoService.incompleteTodo(todoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

}
