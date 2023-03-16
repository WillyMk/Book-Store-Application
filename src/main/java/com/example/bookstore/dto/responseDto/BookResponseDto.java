package com.example.bookstore.dto.responseDto;

import jdk.dynalink.linker.LinkerServices;
import lombok.Data;

import java.util.List;

@Data
public class BookResponseDto {

    private Long id;

    private String name;

    private String categoryName;

    private List<String> authorNames;
}
