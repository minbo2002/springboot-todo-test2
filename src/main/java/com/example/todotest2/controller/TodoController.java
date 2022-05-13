package com.example.todotest2.controller;

import com.example.todotest2.dto.TodoRequest;
import com.example.todotest2.dto.TodoResponse;
import com.example.todotest2.entity.Todo;
import com.example.todotest2.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping(path = "post/create")
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest todoRequest) {
        System.out.println("CREATE");

        Todo todoCreate = todoService.add(todoRequest);

        return new ResponseEntity(todoCreate, HttpStatus.CREATED);
    }

    @GetMapping(path = "get/readOne/{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id) {
        System.out.println("READ ONE");

        Todo todoReadOne = todoService.searchById(id);

        return new ResponseEntity(todoReadOne, HttpStatus.OK);
    }

    @GetMapping(path = "get/readAll")
    public ResponseEntity<List<TodoResponse>> readAll() {
        System.out.println("READ ALL");

        List<Todo> todoReadAll = todoService.searchAll();

        return new ResponseEntity(todoReadAll, HttpStatus.OK);
    }

    @PutMapping(path = "put/update/{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id,
                                               @RequestBody TodoRequest todoRequest) {
        System.out.println("UPDATE");

        Todo todoUpdate = todoService.updateById(id, todoRequest);

        return new ResponseEntity(todoUpdate, HttpStatus.CREATED);
    }

    @DeleteMapping("delete/deleteOne/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        System.out.println("DELETE ONE");

        todoService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "delete/deleteAll")
    public ResponseEntity<?> deleteAll() {
        System.out.println("DELETE ALL");

        todoService.deleteAll();

        return new ResponseEntity<>(HttpStatus.OK);
    }

}