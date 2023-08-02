package com.naman.learnspringsecurity.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class TodoResource {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private static List<Todo> todos = List.of(new Todo("naman", "Learn JWT"),
            new Todo("naman", "Learn Spring Security"),
            new Todo("tom", "Learn React"),
            new Todo("tom", "Learn Spring Security"),
            new Todo("jerry", "Learn Vue"));
    @GetMapping("/todos")
    public List<Todo> retrieveAllTodos(){
        return todos;
    }

    @GetMapping("/users/{username}/todo")
    public List<Todo> retrieveTodosForSpecificUser(@PathVariable String username){
        return todos.stream().filter(todo -> Objects.equals(todo.username(), username)).toList();
    }
    @PostMapping("/users/{username}/todo")
    public void createTodoForSpecificUser(@PathVariable String username
            , @RequestBody Todo todo) {
        logger.info("Create {} for {}", todo, username);
    }
}

record Todo (String username, String description){}
