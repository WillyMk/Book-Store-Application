package com.example.bookstore.controller;

import com.example.bookstore.dto.requestDto.CategoryRequestDto;
import com.example.bookstore.dto.responseDto.CategoryResponseDto;
import com.example.bookstore.model.Category;
import com.example.bookstore.repository.CategoryRepo;
import com.example.bookstore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> saveCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        return new ResponseEntity<CategoryResponseDto>(categoryService.saveCategory(categoryRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<?>> getCategories(){
        return new ResponseEntity<List<?>>(categoryService.getCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        return new ResponseEntity<Category>(categoryService.getCategory(id), HttpStatus.OK);
    }
}
