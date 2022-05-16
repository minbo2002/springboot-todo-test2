package com.example.todotest2.service;

import com.example.todotest2.dto.TodoRequest;
import com.example.todotest2.dto.TodoResponse;
import com.example.todotest2.entity.Todo;

import com.example.todotest2.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    @Override
    public Todo add(TodoRequest todoRequest) {

//        Todo todo = new Todo();
//        todo.setTitle(todoRequest.getTitle());
//        todo.setOrder(todoRequest.getOrder());
//        todo.setCompleted(todoRequest.getCompleted());
//
//        Todo saveToEntity = todoRepository.save(todo);
//
//        return saveToEntity;

         Todo todo = Todo.builder()
                .title(todoRequest.getTitle())
                .order(todoRequest.getOrder())
                .completed(todoRequest.getCompleted())
                .build();

         Todo savedTodoAdd = todoRepository.save(todo);

         return savedTodoAdd;
    }

    @Override
    public Todo searchById(Long id) {

        Todo todoSearchById = todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return todoSearchById;
    }

    @Override
    public List<Todo> searchAll() {

        List<Todo> todoSearchAll = todoRepository.findAll();

        return todoSearchAll;
    }

    @Override
    public Todo updateById(Long id, TodoRequest todoRequest) {

        Todo todoSearchById = todoRepository.findById(id)
                        .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

//        todoSearchById.setTitle(todoRequest.getTitle());
//        todoSearchById.setOrder(todoRequest.getOrder());
//        todoSearchById.setCompleted(todoRequest.getCompleted());
//
//        Todo saveToEntity = todoRepository.save(todoSearchById);
//
//        return saveToEntity;

        todoSearchById.getTitle();
        todoSearchById.getOrder();
        todoSearchById.getCompleted();

        Todo savedTodoUpdate = todoRepository.save(todoSearchById);

        return savedTodoUpdate;
    }

    @Override
    public void deleteById(Long id) {

        todoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {

        todoRepository.deleteAll();
    }
}
