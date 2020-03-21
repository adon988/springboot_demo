package com.base.basetest.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "members")
public class Member {

    //member_id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;
    
    //first_name
    @Column(name = "first_name")
    @NotEmpty(message = "* Please enter first name")
    private String firstName;

    //last_name
    @Column(name = "last_name")
    @NotEmpty(message = "* Please enter last name")
    private String lastName;

    //email
    @Email(message="* Please enter valid email adress")
    @NotEmpty(message = "* Please enter email")
    @Column(name = "email")
    private String email;

}