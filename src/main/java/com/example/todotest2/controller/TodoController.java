package com.example.todotest2.controller;

import com.example.todotest2.dto.TodoRequest;
import com.example.todotest2.dto.TodoResponse;
import com.example.todotest2.entity.Todo;
import com.example.todotest2.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping(path = "post/create")
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest todoRequest) {
        log.info("CREATE");

        Todo todoCreate = todoService.add(todoRequest);

        return new ResponseEntity(todoCreate, HttpStatus.CREATED);
    }

    @GetMapping(path = "get/readOne/{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id) {
        log.info("READ ONE");

        Todo todoReadOne = todoService.searchById(id);

        return new ResponseEntity(todoReadOne, HttpStatus.OK);
    }

    @GetMapping(path = "get/readAll")
    public ResponseEntity<List<TodoResponse>> readAll() {
        log.info("READ ALL");

        List<Todo> todoReadAll = todoService.searchAll();

        return new ResponseEntity(todoReadAll, HttpStatus.OK);
    }

    @PutMapping(path = "put/update/{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id,
                                               @RequestBody TodoRequest todoRequest) {
        log.info("UPDATE");

        Todo todoUpdate = todoService.updateById(id, todoRequest);

        return new ResponseEntity(todoUpdate, HttpStatus.CREATED);
    }

    @DeleteMapping("delete/deleteOne/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        log.info("DELETE ONE");

        todoService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "delete/deleteAll")
    public ResponseEntity<?> deleteAll() {
        log.info("DELETE ALL");

        todoService.deleteAll();

        return new ResponseEntity<>(HttpStatus.OK);
    }

}