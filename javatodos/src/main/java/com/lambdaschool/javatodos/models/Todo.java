package com.lambdaschool.javatodos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "todos")
public class Todo extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoid;

    @Column(nullable = false)
    private String description;

    @Transient
    public boolean completedSwitch;
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    public Todo(String description, User user) {
        this.description = description;
        this.user = user;
    }



    public Todo() {
    }

    public long getTodoid() {
        return todoid;
    }

    public void setTodoid(long todoid) {
        this.todoid = todoid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompletedSwitch() {
        return completedSwitch;
    }

    public void setCompletedSwitch(boolean completedSwitch) {
        this.completedSwitch = completedSwitch;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean CompletedMethodSwitch(){
        return completed;
    }

}
