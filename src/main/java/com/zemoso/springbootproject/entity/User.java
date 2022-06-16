package com.zemoso.springbootproject.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public @Data class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "User name can't be empty")
    @Size(min = 3, max = 20, message = "user name should be min of 3 and max of 20 characters")
    @Column(name = "username")
    private String userName;

    @NotEmpty(message = "Password can't be empty")
    @Size(min = 4, max = 12, message = "Password length must be min of 4 and max of 12 ")
    @Column(name = "password",columnDefinition = "")
    private String password;

    @Column(name = "enabled")
    private int enabled =1;

    @NotEmpty
    @Column(name = "authority")
    private String authority;

    public User() {
    }

}
