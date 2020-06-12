package com.lambdaschool.javatodos.services;

import com.lambdaschool.javatodos.models.Todo;

public interface TodoService {

    Todo updateTodo(Todo todo, long id);

    Todo getTodoById(long id);

    Todo save(Todo todo, long userid);
}
