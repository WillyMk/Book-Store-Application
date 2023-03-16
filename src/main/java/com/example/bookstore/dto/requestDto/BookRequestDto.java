package com.example.bookstore.dto.requestDto;

import lombok.Data;

import java.util.List;

@Data
public class BookRequestDto {

    private String name;

    private Long categoryId;

    private List<Long> authorIds;
}
