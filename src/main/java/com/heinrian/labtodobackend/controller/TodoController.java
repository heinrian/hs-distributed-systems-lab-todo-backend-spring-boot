package com.heinrian.labtodobackend.controller;

import com.heinrian.labtodobackend.model.TodoItem;
import com.heinrian.labtodobackend.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoItemRepository todoRepository;

    @GetMapping
    public List<TodoItem> getAllTodoItems() {
        return todoRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public TodoItem createTodoItem(@RequestBody TodoItem todoItem){
        todoItem = todoRepository.save(todoItem);
        return todoItem;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Optional<TodoItem>> getTodoItemById(@PathVariable String name) {
        Optional<TodoItem> todoItem = todoRepository.findById(name);
        if (todoItem.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(todoItem, HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Optional<TodoItem>> deleteTodoItem(@PathVariable String name) {
        Optional<TodoItem> todoItem = todoRepository.findById(name);
        if (todoItem.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        todoRepository.deleteById(name);
        return new ResponseEntity<>(todoItem, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<TodoItem> updateEmployee(@RequestBody TodoItem todoItemToUpdate) {
        String todoName = todoItemToUpdate.getTodo();
        Optional<TodoItem> todoItem = todoRepository.findById(todoName);
        if (todoItem.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        TodoItem item = todoItem.get();
        item.setPriority(todoItemToUpdate.getPriority());
        final TodoItem updatedTodoItem = todoRepository.save(item);
        return new ResponseEntity<>(updatedTodoItem, HttpStatus.OK);
    }
}
