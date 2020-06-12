package com.lambdaschool.javatodos.controllers;

import com.lambdaschool.javatodos.models.Todo;
import com.lambdaschool.javatodos.models.User;
import com.lambdaschool.javatodos.services.TodoService;
import com.lambdaschool.javatodos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TodoService todoService;


    //Get http://localhost:2019/users/users return all of the users and their todos
    @GetMapping(value = "/users", produces = {"application/json"})
    public ResponseEntity<?> listAllUsers(){
        List<User> myUsers = userService.getAllUsers();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    //Get http://localhost:2019/users/user/{userid} return the user and their todos based off of id
    @GetMapping(value = "/user/{userid}", produces = {"application/json"})
    public ResponseEntity<?> findUserById(@PathVariable long userid){//join table to find user tod0?
        User myUser = userService.getUserById(userid);
        return new ResponseEntity<>(myUser, HttpStatus.OK);
    }

    //Post http://localhost:2019/users/user adds a user
    @PostMapping(value = "/user", consumes = {"application/json"})
    public ResponseEntity<?> addNewUser(@Validated @RequestBody User newUser){
        newUser = userService.save(newUser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRestaurantURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("userid")
                .buildAndExpand(newUser.getUserid()).toUri();
        responseHeaders.setLocation(newRestaurantURI);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //DELETE http://localhost:2019/users/userid/{userid} - Deletes a user based off of their userid and deletes all their associated todos.
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<?> deleteUserById(
            @PathVariable
                    long id)
    {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // http://localhost:2019/users/todoo/user/{userid}
    @PostMapping(value = "/todos/user/{userid}", consumes = {"application/json"})
    public ResponseEntity<?> addTodoToUser(@Validated @RequestBody Todo myTodo, @PathVariable long userid) {
        myTodo = todoService.save(myTodo, userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //http://localhost:2019/users/todos/todob/{userid}
    @PatchMapping(value = "/todos/todo/{id}",
            consumes = {"application/json"})
    public ResponseEntity<?> updateUser(
            @RequestBody User updateUser, @PathVariable long id)
    {
        userService.update(updateUser, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/user/count",
            produces = {"application/json"})
    public ResponseEntity<?> getNumUserEmails()
    {
        return new ResponseEntity<>(userService.getCountUserTodos(),
                HttpStatus.OK);
    }


}