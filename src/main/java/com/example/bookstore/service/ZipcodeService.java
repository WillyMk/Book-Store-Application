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

        ZipcodeResponseDto zip = new ZipcodeResponseDto();
        zip.setId(z.getId());
        zip.setName(z.getName());
        zip.setCityName(z.getCity().getName());
        return zip;
    }

    public List<?> getZipcodes() {
        List<Zipcode> zipcodes = zipcodeRepo.findAll();
        return zipcodes.stream().map(z -> mapZipcodeToData(z)).collect(Collectors.toList());
    }

    private ZipcodeResponseDto mapZipcodeToData(Zipcode z) {
        ZipcodeResponseDto zipcode = new ZipcodeResponseDto();
        zipcode.setName(z.getName());
        zipcode.setId(z.getId());
        zipcode.setCityName(z.getCity().getName());
        return zipcode;
    }


    public Zipcode getZipcode(Long id) {
        Optional<Zipcode> zipcode = zipcodeRepo.findById(id);
        if(zipcode.isPresent()){
            return zipcode.get();
        }
        throw new RuntimeException("Zipcode by id " + id + "not found");
    }
}
