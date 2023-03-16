package com.example.bookstore.controller;

import com.example.bookstore.dto.requestDto.AuthorRequestDto;
import com.example.bookstore.dto.responseDto.AuthorResponseDto;
import com.example.bookstore.model.Author;
import com.example.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponseDto> saveAuthor(@RequestBody AuthorRequestDto authorRequestDto){
        return new ResponseEntity<AuthorResponseDto>(authorService.saveAuthor(authorRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<?>> getAuthors(){
        return new ResponseEntity<List<?>>(authorService.getAuthors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id){
        return new ResponseEntity<Author>(authorService.getAuthor(id), HttpStatus.OK);
    }
}
