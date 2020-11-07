package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{
//   Book save(Book book);
   Book findById(long id);
   List<Book> findByBookName (String bookName);
   List<Book> findByBookNameContaining(String bookName);
//   Page<Book> findAllBy(Pageable pageable);
}
