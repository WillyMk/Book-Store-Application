package com.example.bookstore.model;

import com.example.bookstore.dto.responseDto.AuthorResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "zipcode_id")
    private Zipcode zipcode;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public static AuthorResponseDto mapAuthorToData(Author a){
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setId(a.getId());
        authorResponseDto.setName(a.getName());
        authorResponseDto.setZipcodeName(a.getZipcode().getName());
        List<String> bookNames = new ArrayList<>();
        List<Book> books = a.getBooks();
        for(Book book: books){
            bookNames.add(book.getName());
        }
        authorResponseDto.setBookNames(bookNames);
        return authorResponseDto;
    }
}
