package com.base.basetest.controllers;

import com.base.basetest.models.Book;
import com.base.basetest.repositories.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
@RestController
@RequestMapping(value="/api")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/v1/book")
    public java.util.List<Book> getAll() {
        return bookRepository.findAll();
    }
    
}