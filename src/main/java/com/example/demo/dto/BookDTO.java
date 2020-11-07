package com.example.demo.dto;


import com.example.demo.model.Book;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BookDTO {

    @JsonProperty("id")
    private long bookId;

    @JsonProperty("book_name")
    private String bookName;

    @JsonProperty("author")
    private String author;

    @JsonProperty("publish_year")
    private int publishYear;

    public BookDTO() {
    }

    public BookDTO(long bookId,String bookName, String author, int publishYear) {
        this.bookName = bookName;
        this.author = author;
        this.publishYear = publishYear;
    }

    public long getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }
}
