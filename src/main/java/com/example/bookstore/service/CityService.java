package com.example.bookstore.service;

import com.example.bookstore.dto.requestDto.CityRequestDto;
import com.example.bookstore.dto.responseDto.CityResponseDto;
import com.example.bookstore.model.City;
import com.example.bookstore.repository.CityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    private  final CityRepo cityRepo;
    public CityResponseDto saveCity(CityRequestDto cityRequestDto) {
        City city = new City();
        city.setName(cityRequestDto.getName());

        City c = cityRepo.save(city);

        CityResponseDto cityResponseDto = new CityResponseDto();
        cityResponseDto.setId(c.getId());
        cityResponseDto.setName(c.getName());
        return cityResponseDto;
    }

    public Page<?> getCity(Integer page, Integer pageSize) {
        if (page < 1) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        return cityRepo.findAll(pageable).map(c -> mapToResponseDto(c));
    }

    private CityResponseDto mapToResponseDto(City c) {
        CityResponseDto city = new CityResponseDto();
        city.setId(c.getId());
        city.setName(c.getName());
        return city;
    }

    public City getCityById(Long id) {
        System.out.println("Id data " + id);
        Optional<City> city = cityRepo.findById(id);
        if(city.isPresent()){
            return city.get();
        }
        throw new RuntimeException("City by id " + id + "not found");
    }
}
