package com.todoapp.service;

import com.todoapp.model.Todo;
import com.todoapp.model.TodoDto;
import com.todoapp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public Long save(TodoDto todoDto){

        Todo todo = todoDto.toEntity();

        return  todoRepository.save(todo).getId();
    }

    // Todo 전체 조회
    public List<Todo> findAllTodo(boolean orderSate){
        if(orderSate){
            log.info("TODO List 오름차순");
            return todoRepository.findAllByUseYnEqualsOrderByRegDateAsc("Y");
        } else {
            log.info("TODO List 내림차순");
            return todoRepository.findAllByUseYnEqualsOrderByRegDateDesc("Y");
        }
    }

    public Todo findTodo(Long id){
        return todoRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updateTodoComplted(Long id, boolean completed) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if(!optionalTodo.isPresent()){
            throw new RuntimeException("TODO List 미존재");
        }

        Todo todo = optionalTodo.get();
        todo.setCompleted(completed);

        todoRepository.save(todo);
    }

    // Todo 삭제(DB 업데이트)
    @Transactional
    public void updateTodoUseYn(Long id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if(!optionalTodo.isPresent()){
            throw new RuntimeException("TODO List 미존재");
        }

        Todo todo = optionalTodo.get();


        todo.setUseYn("N");

        todoRepository.save(todo);
    }

    // Todo 전체 삭제(DB 업데이트)
    @Transactional
    public int updateTodoAllClear() {

        todoRepository.updateTodoAllClear();

        return 1;
    }

}
