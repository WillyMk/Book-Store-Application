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

        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(book1.getId());
        bookResponseDto.setName(book1.getName());
        bookResponseDto.setCategoryName(book1.getCategory().getName());
        return getBookResponseDto(book1, bookResponseDto);
    }

    public List<?> getBooks() {
        List<Book> books = bookRepo.findAll();
        return books.stream().map(book -> mapBookToData(book)).collect(Collectors.toList());
    }

    private BookResponseDto mapBookToData(Book book) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setName(book.getName());
        bookResponseDto.setCategoryName(book.getCategory().getName());
        return getBookResponseDto(book, bookResponseDto);
    }

    private BookResponseDto getBookResponseDto(Book book1, BookResponseDto bookResponseDto) {
        List<String> authorNames = new ArrayList<>();
        List<Author> authors = book1.getAuthors();
        for(Author a : authors){
            authorNames.add(a.getName());
        }
        bookResponseDto.setAuthorNames(authorNames);
        return bookResponseDto;
    }

    public Book getBook(Long id) {
        Optional<Book> book = bookRepo.findById(id);
        if(book.isPresent()){
            return book.get();
        }
        throw new RuntimeException("id not found");
    }
}
