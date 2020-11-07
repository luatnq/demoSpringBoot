package com.example.demo.service;

import com.example.demo.dto.BookDTO;
import com.example.demo.mapper.ModelMapper;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookRepository bookRepository;


    public BookDTO createBook(BookDTO bookDTO) {
        BookDTO bookDTO1 = new BookDTO(bookDTO.getBookId(), bookDTO.getBookName(),
                bookDTO.getAuthor(), bookDTO.getPublishYear());
        Book book = bookRepository.save(convertBookEntity(bookDTO1));
        return convertToBookDTO(book);
    }

    public List<BookDTO> getAllBook(String bookName) {
        List<BookDTO> bookDTOs = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        if (bookName == null) {
            bookRepository.findAll().forEach(books::add);
        } else {
            bookRepository.findByBookNameContaining(bookName).forEach(books::add);
        }
        for (Book b : books) {
            bookDTOs.add(convertToBookDTO(b));
        }
        return bookDTOs;
    }

    public BookDTO getBookById(long id) {
        Book bookData = bookRepository.findById(id);
        if (Objects.nonNull(bookData)) {
            return convertToBookDTO(bookData);
        } else {
            return null;
        }
    }

    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
        Book book= bookRepository.findById(id);
        System.out.println(book);
    }

    public BookDTO updateBook(BookDTO bookDTO){
        Book book = bookRepository.findById(bookDTO.getBookId());
        if (Objects.nonNull(book)) {
            book.setBookName(bookDTO.getBookName());
            book.setAuthor(bookDTO.getAuthor());
            book.setPublishYear(bookDTO.getPublishYear());

            Book saved = bookRepository.saveAndFlush(book);
            return convertToBookDTO(saved);
        }else {
            return null;
        }

    }

    public Page<Book> listBook(Model model,Integer page , Integer size){
        Pageable pageable= PageRequest.of(page,size);
        Page<Book> allBook= bookRepository.findAll(pageable);
        allBook.nextPageable();
//        model.addAttribute("listBook",bookRepository.findAll(pageable));
       // return "book-list";
        return allBook;
    }
    private BookDTO convertToBookDTO(Book book){
        return modelMapper.bookToBookDTO(book);
    }
    private Book convertBookEntity(BookDTO bookDTO){
        return modelMapper.bookDTOToBook(bookDTO);
    }
}
