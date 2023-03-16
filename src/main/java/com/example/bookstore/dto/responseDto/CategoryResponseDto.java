package com.example.bookstore.dto.responseDto;

import com.example.bookstore.model.Book;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponseDto {

    private Long id;

    private String name;

    private List<Book> books;
}
