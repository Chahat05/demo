package com.interview.demo.controller;

import jakarta.persistence.*;

@Entity
@Table(name="student")
public class StudentEntity {

    public StudentEntity() {
    }

    public StudentEntity(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }



    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name="email")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "student [Id=" + id + ", name=" + name + ", emailId=" + email + "]";
    }
}




