package com.example.mangalfera.repository;

import com.example.mangalfera.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {}