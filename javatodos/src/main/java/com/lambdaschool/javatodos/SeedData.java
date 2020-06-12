package com.lambdaschool.javatodos;

import com.lambdaschool.javatodos.models.Role;
import com.lambdaschool.javatodos.models.Todo;
import com.lambdaschool.javatodos.models.User;
import com.lambdaschool.javatodos.services.RoleService;
import com.lambdaschool.javatodos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;


    @Override
    public void run(String[] args) throws Exception {
        Role r1 = new Role("parent");
        Role r2 = new Role("child1");
        Role r3 = new Role("child2");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);

        User u1 = new User("admin",
                "password",
                "admin@lambdaschool.local");
        u1.addRole(r1);
        u1.getTodos()
                .add(new Todo("Finish java-orders-swagger",
                        u1));
        u1.getTodos()
                .add(new Todo("Feed the turtles",
                        u1));
        u1.getTodos()
                .add(new Todo("Complete the sprint challenge",
                        u1));

        userService.save(u1);

        User u2 = new User("cinnamon",
                "1234567",
                "cinnamon@lambdaschool.local");
        u2.addRole(r2);
        u2.getTodos()
                .add(new Todo("Walk the dogs",
                        u2));
        u2.getTodos()
                .add(new Todo("provide feedback to my instructor",
                        u2));
        userService.save(u2);

        // user

        User u3 = new User("barnbarn",
                "ILuvM4th!",
                "barnbarn@lambdaschool.local");
        u3.addRole(r2);
        userService.save(u3);


        User u4 = new User("puttat",
                "password",
                "puttat@school.lambda");
        u4.addRole(r3);
        userService.save(u4);

        User u5 = new User("misskitty",
                "password",
                "misskitty@school.lambda");
        userService.save(u5);
    }
}




//import com.lambdaschool.javatodos.models.Todo;
//import com.lambdaschool.javatodos.models.User;
//import com.lambdaschool.javatodos.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Transactional
//@Component
//public class com.lambdaschool.javatodos.SeedData implements CommandLineRunner
//{
//    @Autowired
//    UserService userService;
//
//    @Transactional
//    @Override
//    public void run(String[] args) throws Exception
//    {
//        User u1 = new User("admin",
//                "password",
//                "admin@lambdaschool.local");
//        u1.getTodos()
//                .add(new Todo("Give Joe access rights",
//                        u1));
//        u1.getTodos()
//                .add(new Todo("Change the color of the home page",
//                        u1));
//
//        userService.save(u1);
//
//        User u2 = new User("cinnamon",
//                "1234567",
//                "cinnamon@lambdaschool.local");
//        u2.getTodos()
//                .add(new Todo("Take a nap",
//                        u2));
//        u2.getTodos()
//                .add(new Todo("Rearrange my hutch",
//                        u2));
//        u2.getTodos()
//                .add(new Todo("Groom my fur",
//                        u2));
//        userService.save(u2);
//
//        User u3 = new User("barnbarn",
//                "ILuvM4th!",
//                "barnbarn@lambdaschool.local");
//        u3.getTodos()
//                .add(new Todo("Rearrange my hutch",
//                        u3));
//        userService.save(u3);
//
//        User u4 = new User("puttat",
//                "password",
//                "puttat@school.lambda");
//        userService.save(u4);
//
//        User u5 = new User("misskitty",
//                "password",
//                "misskitty@school.lambda");
//        userService.save(u5);
//    }
//}