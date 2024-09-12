package com.example.country.service;

import com.example.country.pojo.University;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountryService {
    University[] getAllCountryUniversity();
    List<University> getUniversityByCountry(List<String> country);
}
