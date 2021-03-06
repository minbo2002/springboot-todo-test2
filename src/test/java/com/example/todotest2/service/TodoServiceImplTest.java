package com.example.todotest2.service;

import com.example.todotest2.dto.TodoRequest;
import com.example.todotest2.dto.TodoResponse;
import com.example.todotest2.entity.Todo;
import com.example.todotest2.repository.TodoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoServiceImplTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoServiceImpl todoService;

    @Test
    void add() {
        // given
        // when
        when(this.todoRepository.save(any(Todo.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        TodoRequest expected = new TodoRequest();
        expected.setTitle("test title");
        expected.setOrder(1L);
        expected.setCompleted(false);

        Todo actual = todoService.add(expected);

        // then
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getOrder(), actual.getOrder());
        assertEquals(expected.getCompleted(), actual.getCompleted());
    }

    @Test
    void searchById() {

        // given
        Todo todo = Todo.builder()
                        .id(123L)
                        .title("title")
                        .order(0L)
                        .completed(false)
                        .build();

        Optional<Todo> optional = Optional.of(todo);

        given(todoRepository.findById(anyLong()))
            .willReturn(optional);

        // when
        Todo actual = todoService.searchById(123L);

        Todo expected = optional.get();

        // then
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getOrder(), actual.getOrder());
        assertEquals(expected.getCompleted(), actual.getCompleted());
    }

    @Test
    public void searchByIdFailed() {
        // given
        given(todoRepository.findById(anyLong()))
                .willReturn(Optional.empty());
        // when
        // then
        assertThrows(ResponseStatusException.class, () -> {
            todoService.searchById(123L);

        });
    }

    @Test
    public void searchAll() {
        // given
        Todo todo = Todo.builder()
                        .id(1L)
                        .title("all_title")
                        .order(10L)
                        .completed(true)
                        .build();

        Todo todo1 = Todo.builder()
                         .id(2L)
                         .title("all_title2")
                         .order(12L)
                         .completed(true)
                         .build();

        given(todoRepository.findAll())
                .willReturn(List.of(todo, todo1));

        // when
        List<Todo> list = todoService.searchAll();
        // then
        assertThat(list).isNotNull();
        assertThat(list.size()).isEqualTo(2);
    }
}