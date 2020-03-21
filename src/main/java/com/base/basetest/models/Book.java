package com.base.basetest.models;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue
    private Integer bookid;
    
    private String name;
    private String author;

}