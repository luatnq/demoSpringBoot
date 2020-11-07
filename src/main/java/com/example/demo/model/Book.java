package com.example.demo.model;

import com.example.demo.dto.BookDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "books")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author")
    private String author;

    @Column(name = "publish_year")
    private int publishYear;

    public Book() {
    }

    public Book(long id,String bookName, String author, int publishYear) {
        this.bookName = bookName;
        this.author = author;
        this.publishYear = publishYear;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }
}
