package com.example.search.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
public class SearchController {

    private final RestTemplate restTemplate;

    public SearchController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/weather/search")
    @HystrixCommand(fallbackMethod = "getDetailsFallback")
    public Map<String, Object> getDetails() {
        try {
//            Thread.sleep(3000);

            CompletableFuture<String> studentTeacherFuture = CompletableFuture.supplyAsync(() ->
                    restTemplate.getForObject("http://student/student", String.class)
            );

            CompletableFuture<String> detailsFuture = CompletableFuture.supplyAsync(() ->
                    restTemplate.getForObject("http://details/details/port", String.class)
            );

            CompletableFuture.allOf(studentTeacherFuture, detailsFuture).join();

            String studentTeacher = studentTeacherFuture.get();
            String details = detailsFuture.get();

            Map<String, Object> result = new HashMap<>();
            result.put("student", studentTeacher);
            result.put("details", details);
            return result;

        } catch (Exception ex) {
            return getDetailsFallback();
        }
    }

    // sync
    public Map<String, Object> getDetailsFallback() {
        Map<String, Object> fallbackResult = new HashMap<>();
        fallbackResult.put("student", "Fallback: Service not available");
        fallbackResult.put("details", "Fallback: Service not available");
        return fallbackResult;
    }
}
