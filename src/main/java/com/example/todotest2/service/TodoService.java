package com.example.todotest2.service;

import com.example.todotest2.dto.TodoRequest;
import com.example.todotest2.entity.Todo;
import lombok.extern.java.Log;

import java.util.List;

public interface TodoService {

    public Todo add(TodoRequest todoRequest);

    public Todo searchById(Long id);

    public List<Todo> searchAll();

    public Todo updateById(Long id, TodoRequest todoRequest);

    public void deleteById(Long id);

    public void deleteAll();
}
