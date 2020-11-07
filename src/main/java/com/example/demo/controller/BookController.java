package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.BinaryOperator;

@RestController
@RequestMapping("api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;


    @PostMapping()
    public ResponseEntity<BookDTO> post(@RequestBody BookDTO bookDTO){
        try{
            return new ResponseEntity<>(bookService.createBook(bookDTO),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<List<BookDTO>> getAllBooks(@RequestParam(required = false) String bookName){
        try{
            List<BookDTO> bookDTOList= bookService.getAllBook(bookName);
            if (bookDTOList.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(bookDTOList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") long id) {
        BookDTO bookDTO= bookService.getBookById(id);
        if (bookDTO!=null) {
            return new ResponseEntity<>( bookDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/")
    public ResponseEntity<Page<Book>> getBookPageable(Model model, @RequestParam(name="page", required = false) Integer page,
                                @RequestParam(name="size", required=false) Integer size){
        return  new ResponseEntity<>(bookService.listBook(model, page, size),HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<BookDTO> putBook(@RequestBody BookDTO bookDTO) {
        BookDTO bookDTO1= bookService.updateBook(bookDTO);
        if (bookDTO1!=null) {
           return new ResponseEntity<>(bookDTO1,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
        try {
            bookService.deleteBookById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}