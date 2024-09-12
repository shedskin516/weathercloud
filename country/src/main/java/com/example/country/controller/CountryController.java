package com.example.country.controller;

import com.example.country.pojo.University;
import com.example.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/university")
public class CountryController {
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping()
    public ResponseEntity<University[]> getAllCountryUniversity() {
        return new ResponseEntity<>(countryService.getAllCountryUniversity(), HttpStatus.OK);
    }

    @GetMapping(params = "country")
    // a request example: .../university?country=Poland,Chile,Thailand
    public ResponseEntity<List<University>> getUniversityByCountry(@RequestParam("country") List<String> country) {
        return new ResponseEntity<>(countryService.getUniversityByCountry(country), HttpStatus.OK);
    }
}
