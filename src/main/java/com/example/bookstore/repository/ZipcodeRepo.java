package com.example.bookstore.repository;

import com.example.bookstore.model.Zipcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipcodeRepo extends JpaRepository<Zipcode, Long> {
}
