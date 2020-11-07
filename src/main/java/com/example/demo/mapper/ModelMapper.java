package com.example.demo.mapper;

import com.example.demo.dto.BookDTO;
import com.example.demo.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ModelMapper {
    @Mapping(target = "bookId", source = "id")
    BookDTO bookToBookDTO(Book book);
    @Mapping(target = "id",source = "bookId")
    Book bookDTOToBook(BookDTO bookDTO);
}
