package com.example.bookstore.controller;

import com.example.bookstore.dto.requestDto.BookRequestDto;
import com.example.bookstore.dto.responseDto.BookResponseDto;
import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDto> saveBook(@RequestBody BookRequestDto bookRequestDto){
        return new ResponseEntity<BookResponseDto>(bookService.saveBook(bookRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<?>> getBooks(){
        return new ResponseEntity<List<?>>(bookService.getBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id){
        return new ResponseEntity<Book>(bookService.getBook(id), HttpStatus.OK);
    }
}
