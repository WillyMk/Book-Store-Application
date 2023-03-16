package com.example.bookstore.service;

import com.example.bookstore.dto.requestDto.CategoryRequestDto;
import com.example.bookstore.dto.responseDto.CategoryResponseDto;
import com.example.bookstore.model.Category;
import com.example.bookstore.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepo categoryRepo;
    public CategoryResponseDto saveCategory(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());

        Category c = categoryRepo.save(category);

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(c.getId());
        categoryResponseDto.setName(c.getName());
        return categoryResponseDto;
    }

    public List<?> getCategories() {
        List<Category> categories = categoryRepo.findAll();
        return categories.stream().map(category -> mapCategoryToData(category)).collect(Collectors.toList());
    }

    private CategoryResponseDto mapCategoryToData(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        categoryResponseDto.setBooks(category.getBooks());
        return categoryResponseDto;
    }

    public Category getCategory(Long id) {
        Optional<Category> category = categoryRepo.findById(id);
        if(category.isPresent()){
            return category.get();
        }
        throw new RuntimeException("Category by id " + id + "not found");
    }
}
