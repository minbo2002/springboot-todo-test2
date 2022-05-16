package com.example.todotest2.controller;

import com.example.todotest2.dto.TodoRequest;
import com.example.todotest2.entity.Todo;
import com.example.todotest2.service.TodoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    TodoServiceImpl todoServiceImpl;

    private Todo todo;

    @BeforeEach
    void setup() {
        todo = Todo.builder()
                   .id(123L)
                   .title("test title")
                   .order(0L)
                   .completed(false)
                   .build();
    }

    @Test
    void create() throws Exception {
        // given
        // when
        when(this.todoServiceImpl.add(any(TodoRequest.class)))
                .then((i) -> {
                   TodoRequest todoRequest = i.getArgument(0, TodoRequest.class);

                   return new Todo(this.todo.getId(),
                           todoRequest.getTitle(),
                           this.todo.getOrder(),
                           this.todo.getCompleted());
                });
        // then
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setTitle("another title");

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(todoRequest);

        this.mvc.perform(post("/post/create").
                contentType(MediaType.APPLICATION_JSON).
                content(content)).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("another title"));

    }

    @Test
    void readOne() {
    }
}