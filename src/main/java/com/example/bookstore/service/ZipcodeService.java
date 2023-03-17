package com.example.bookstore.service;

import com.example.bookstore.dto.requestDto.ZipcodeRequestDto;
import com.example.bookstore.dto.responseDto.ZipcodeResponseDto;
import com.example.bookstore.model.City;
import com.example.bookstore.model.Zipcode;
import com.example.bookstore.repository.ZipcodeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZipcodeService {

    private final ZipcodeRepo zipcodeRepo;
    private final CityService cityService;
    public ZipcodeResponseDto saveZipcodes(ZipcodeRequestDto zipcodeRequestDto) {
        Zipcode zipcode = new Zipcode();
        zipcode.setName(zipcodeRequestDto.getName());
        City c = cityService.getCityById(zipcodeRequestDto.getCityId());

        zipcode.setCity(c);

        Zipcode z = zipcodeRepo.save(zipcode);
        return Zipcode.mapZipcodeToData(z);
    }

    public List<?> getZipcodes() {
        List<Zipcode> zipcodes = zipcodeRepo.findAll();
        return zipcodes.stream().map(z -> Zipcode.mapZipcodeToData(z)).collect(Collectors.toList());
    }


    public Zipcode getZipcode(Long id) {
        Optional<Zipcode> zipcode = zipcodeRepo.findById(id);
        if(zipcode.isPresent()){
            return zipcode.get();
        }
        throw new RuntimeException("Zipcode by id " + id + "not found");
    }
}
