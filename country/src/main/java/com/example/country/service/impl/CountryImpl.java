package com.example.country.service.impl;

import com.example.country.pojo.University;
import com.example.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class CountryImpl implements CountryService {

    @Value("${api.url}")
    private String apiUrl;
    private final RestTemplate restTemplate;
    private final ExecutorService executorService;

    @Autowired
    public CountryImpl(RestTemplate restTemplate, ExecutorService executorService) {
        this.restTemplate = restTemplate;
        this.executorService = executorService;
    }

    @Override
    public University[] getAllCountryUniversity() {
        return restTemplate.getForObject(apiUrl, University[].class);
    }

    @Override
    public List<University> getUniversityByCountry(List<String> countries) {
//        List<String> countryArr = List.of(countries.split("&"));

        // CompletableFuture send request
        List<CompletableFuture<List<University>>> futures = countries.stream()
                .map(url -> apiUrl + "?country=" + url)
                .map(this::fetchDataAsync)
                .collect(Collectors.toList());

        // CompletableFuture.allOf to wait all results finish
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        // wait all results and combine
        return allFutures.thenApply(v -> futures.stream()
                .map(CompletableFuture::join) // get all results
                .flatMap(List::stream) // combine results
                .collect(Collectors.toList())) // List<University>
                .join();
    }

    public CompletableFuture<List<University>> fetchDataAsync(String url) {
        return CompletableFuture.supplyAsync(() -> {
            return restTemplate.getForObject(url, List.class);
        }, executorService);
    }

}
