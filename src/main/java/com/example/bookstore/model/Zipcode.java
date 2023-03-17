package com.example.bookstore.model;

import com.example.bookstore.dto.responseDto.ZipcodeResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zipcode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;

    public static ZipcodeResponseDto mapZipcodeToData(Zipcode z) {
        ZipcodeResponseDto zipcode = new ZipcodeResponseDto();
        zipcode.setName(z.getName());
        zipcode.setId(z.getId());
        zipcode.setCityName(z.getCity().getName());
        return zipcode;
    }
}
