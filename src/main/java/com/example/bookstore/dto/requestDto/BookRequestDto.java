package com.example.bookstore.dto.requestDto;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Category;
import com.example.bookstore.service.CategoryService;
import lombok.Data;

import java.util.List;

@Data
public class BookRequestDto {

    private final CategoryService categoryService;

    private String name;

    private Long categoryId;

    private List<Long> authorIds;
}
