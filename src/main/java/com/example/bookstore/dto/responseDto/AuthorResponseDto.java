package com.example.bookstore.dto.responseDto;

import com.example.bookstore.model.Zipcode;
import lombok.Data;

import java.util.List;

@Data
public class AuthorResponseDto {

    private Long id;
    private String name;

    private String zipcodeName;

    private List<String> bookNames;
}
