package com.todoapp.controller;

import com.todoapp.model.Todo;
import com.todoapp.model.TodoDto;
import com.todoapp.model.UpdateTodoRequest;
import com.todoapp.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos/save")
    public String createJsonTodo(@RequestBody TodoDto todoDto, BindingResult bindingResult){
        log.info("Post : Todo Save");

        if(bindingResult.hasErrors()){
            return "todo error";
        }
        Todo todo = todoDto.toEntity();
        todoService.save(todoDto);

        return "ok";
    }

    // Todo 완료 상태 업데이트
    @PutMapping("/todos/{id}")
    public String updateTodo(
            @PathVariable("id") Long id,
            @RequestBody UpdateTodoRequest request
    ){
        log.info("Put : Todo update");

        todoService.updateTodoComplted(id, request.isCompleted());

        Todo findTodo = todoService.findTodo(id);

        if(request.isCompleted() == findTodo.isCompleted()){
            return "ok";
        } else {
            return "fail";
        }
    }

    // Todo 삭제(DB 업데이트)
    @PutMapping("/todos/delete/{id}")
    public String deleteTodo(
            @PathVariable("id") Long id
    ){
        log.info("Delete : Todo Delete");

        todoService.updateTodoUseYn(id);

        Todo findTodo = todoService.findTodo(id);

        if(findTodo.getUseYn().equals("N")){
            return "ok";
        } else {
            return "fail";
        }
    }

    @PutMapping("/todos/clear")
    public String clearAllTodo(){
        log.info("Clear : Todo All Clear");

        int result = todoService.updateTodoAllClear();

        if(result > 0){
            return "ok";
        } else {
            return "fail";
        }
    }

    // Todo 목록
    @GetMapping("/todos/{orderState}")
    public List<Todo> list(@PathVariable("orderState") Boolean orderState){
        log.info("Get : Todos List");

        return todoService.findAllTodo(orderState);
    }

}
