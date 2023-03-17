package com.example.bookstore.service;

import com.example.bookstore.dto.requestDto.BookRequestDto;
import com.example.bookstore.dto.responseDto.BookResponseDto;
import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Category;
import com.example.bookstore.repository.BookRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepo;
    private final AuthorService authorService;

    private final CategoryService categoryService;

    @Transactional
    public BookResponseDto saveBook(BookRequestDto bookRequestDto) {
        Book book = new Book();
        book.setName(bookRequestDto.getName());
        if(bookRequestDto.getCategoryId() == null){
            throw new IllegalArgumentException("You need to input a category");
        }
        Category category = categoryService.getCategory(bookRequestDto.getCategoryId());
        book.setCategory(category);
        if(bookRequestDto.getAuthorIds().isEmpty()){
            throw new IllegalArgumentException("You need atleast one author");
        }else {
            List<Author> authors = new ArrayList<>();
            for (Long authorId : bookRequestDto.getAuthorIds()) {
                Author a = authorService.getAuthor(authorId);
                authors.add(a);
            }
            book.setAuthors(authors);
        }

        Book book1 = bookRepo.save(book);

        return Book.mapBookToData(book1);
    }

    public List<?> getBooks() {
        List<Book> books = bookRepo.findAll();
        return books.stream().map(book -> Book.mapBookToData(book)).collect(Collectors.toList());
    }

    public Book getBook(Long id) {
        Optional<Book> book = bookRepo.findById(id);
        if(book.isPresent()){
            return book.get();
        }
        throw new RuntimeException("id not found");
    }
}
