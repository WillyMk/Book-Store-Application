package com.example.bookstore.controller;

import com.example.bookstore.dto.requestDto.CityRequestDto;
import com.example.bookstore.dto.responseDto.CityResponseDto;
import com.example.bookstore.model.City;
import com.example.bookstore.service.CityService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<?>> getCity(@RequestParam(required = false, name = "page") Integer page, @RequestParam(required = false, name = "pageSize" ) Integer pageSize){
        return new ResponseEntity<List<?>>(cityService.getCity(page, pageSize), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id){
        return new ResponseEntity<City>(cityService.getCityById(id), HttpStatus.OK);
    }

}
