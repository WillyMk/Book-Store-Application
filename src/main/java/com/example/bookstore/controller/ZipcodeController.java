package com.example.bookstore.controller;

import com.example.bookstore.dto.requestDto.ZipcodeRequestDto;
import com.example.bookstore.dto.responseDto.ZipcodeResponseDto;
import com.example.bookstore.model.Zipcode;
import com.example.bookstore.service.ZipcodeService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/zipcode")
public class ZipcodeController {

    private final ZipcodeService zipcodeService;

    @PostMapping
    public ResponseEntity<ZipcodeResponseDto> saveZipcode(@RequestBody ZipcodeRequestDto zipcodeRequestDto){
        return new ResponseEntity<ZipcodeResponseDto>(zipcodeService.saveZipcodes(zipcodeRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<?>> getZipcodes(){
        return new ResponseEntity<List<?>>(zipcodeService.getZipcodes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zipcode> getZipcode(@PathVariable Long id){
        return new ResponseEntity<Zipcode>(zipcodeService.getZipcode(id), HttpStatus.OK);
    }
}
