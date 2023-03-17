package com.example.bookstore.controller;

import com.example.bookstore.dto.requestDto.CityRequestDto;
import com.example.bookstore.dto.responseDto.CityResponseDto;
import com.example.bookstore.model.City;
import com.example.bookstore.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    @PostMapping
    public ResponseEntity<CityResponseDto> saveCity(@RequestBody CityRequestDto cityRequestDto){
        return new ResponseEntity<CityResponseDto>(cityService.saveCity(cityRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getCity(@RequestParam(required = false, name = "page") Integer page, @RequestParam(required = false, name = "pageSize" ) Integer pageSize){
        return ResponseEntity.status(HttpStatus.OK).body(cityService.getCity(page, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id){
        return new ResponseEntity<City>(cityService.getCityById(id), HttpStatus.OK);
    }

}
