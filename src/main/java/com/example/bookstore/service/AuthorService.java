package com.example.bookstore.service;

import com.example.bookstore.dto.requestDto.AuthorRequestDto;
import com.example.bookstore.dto.responseDto.AuthorResponseDto;
import com.example.bookstore.dto.responseDto.BookResponseDto;
import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Zipcode;
import com.example.bookstore.repository.AuthorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;
    private final ZipcodeService zipcodeService;

    public AuthorResponseDto saveAuthor(AuthorRequestDto authorRequestDto) {
        Author author = new Author();
        author.setName(authorRequestDto.getName());
        Zipcode z = zipcodeService.getZipcode(authorRequestDto.getZipcodeId());
        author.setZipcode(z);

        Author a = authorRepo.save(author);
        return Author.mapAuthorToData(a);

    }
    public List<?> getAuthors() {
        List<Author> authors = authorRepo.findAll();
        return authors.stream().map(a -> Author.mapAuthorToData(a)).collect(Collectors.toList());
    }

    public Author getAuthor(Long id) {
        Optional<Author> author = authorRepo.findById(id);

        if(author.isPresent()){
            return author.get();
        }
        throw new RuntimeException("Author by id" + id + "not found");
    }
}
