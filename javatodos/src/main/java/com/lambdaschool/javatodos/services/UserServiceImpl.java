package com.lambdaschool.javatodos.services;

import com.lambdaschool.javatodos.models.Role;
import com.lambdaschool.javatodos.models.Todo;
import com.lambdaschool.javatodos.models.User;
import com.lambdaschool.javatodos.repositories.UserRepository;
import com.lambdaschool.javatodos.views.UserNameCountTodos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleService roleService;


    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        userrepos.findAll().iterator().forEachRemaining(list::add);//comes back as iterator then converts to arraylist
        return list;
    }

    public User getUserById(long id) throws EntityNotFoundException
    {
        return userrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public User save(User user) {
        User newUser = new User();

        //Saves all the current details
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setPrimaryemail(user.getPrimaryemail());
        //Tod0 must exist

        //loops and Adds the tod0 that the user wants saved to their tod0 list
        for (Todo t: user.getTodos() ){

            Todo newTodo = new Todo(t.getDescription(), newUser);
            newUser.getTodos().add(newTodo);

        }

        //loops and adds the role
        for (Role r : user.getRoles()){
            Role newRole = roleService.getRoleById(r.getRoleid());
            newUser.addRole(newRole);
        }
        //Returns the newly created user
        return userrepos.save(newUser);

    }

    //Updates the user, very similar to above
    @Override
    public User update(User user, long id) {
        User updateUser = new User();

        updateUser.setUsername(user.getUsername());
        updateUser.setPassword(user.getPassword());
        updateUser.setPrimaryemail(user.getPrimaryemail());

        for (Todo t: user.getTodos() ){

            Todo updateTodo = new Todo(t.getDescription(), updateUser);
            updateUser.getTodos().add(updateTodo);

        }

        for (Role r : user.getRoles()){
            Role updateRole = roleService.getRoleById(r.getRoleid());
            updateUser.addRole(updateRole);
        }

        return null;

    }

    @Transactional
    @Override
    public void delete(long id)
    {
        userrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User id " + id + " not found!"));
        userrepos.deleteById(id);
    }

    @Override
    public List<UserNameCountTodos> getCountUserTodos() {
        return userrepos.getCountUserTodos();
    }


}