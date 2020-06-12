package com.lambdaschool.javatodos.services;

import com.lambdaschool.javatodos.models.User;
import com.lambdaschool.javatodos.views.UserNameCountTodos;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(long id);

    User save(User user);

    User update(User user, long id);

    void delete(long id);

    List<UserNameCountTodos> getCountUserTodos();
}
