package com.lambdaschool.javatodos.repositories;

import com.lambdaschool.javatodos.models.Todo;
import com.lambdaschool.javatodos.models.User;
import com.lambdaschool.javatodos.views.JustTheCount;
import com.lambdaschool.javatodos.views.UserNameCountTodos;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

 User findByUsername(String username);

 List<User> findByUsernameContainingIgnoreCase(String name);

 @Query(value = "SELECT u.username as usernamerpt, count(ue.todoid) as counttodos FROM users u JOIN usertodos ue ON u.userid = ue.userid GROUP BY u.username",
         nativeQuery = true)
 List<UserNameCountTodos> getCountUserTodos();
}
