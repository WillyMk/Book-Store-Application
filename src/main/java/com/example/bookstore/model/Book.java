package com.example.bookstore.model;

import com.example.bookstore.dto.requestDto.BookRequestDto;
import com.example.bookstore.dto.responseDto.BookResponseDto;
import com.example.bookstore.service.CategoryService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    public static BookResponseDto mapBookToData(Book book) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setName(book.getName());
        bookResponseDto.setCategoryName(book.getCategory().getName());
        List<String> authorNames = new ArrayList<>();
        List<Author> authorDetails = book.getAuthors();
        for(Author a : authorDetails){
            authorNames.add(a.getName());
        }
        bookResponseDto.setAuthorNames(authorNames);
        //Stream method
//        bookResponseDto.setAuthorNames(book1.getAuthors().parallelStream()
//                .map(Author::getName).collect(Collectors.toList()));
        return bookResponseDto;
    }
}
