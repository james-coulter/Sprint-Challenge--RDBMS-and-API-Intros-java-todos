package com.lambdaschool.javatodos.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleid;

    @Column(nullable = false, unique = true)
    private String rolename;


    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();





    public Role(String rolename) {
        this.rolename = rolename;

    }

    public Role(){}

    public long getRoleid() {
        return roleid;
    }


    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
