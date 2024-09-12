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

//    @GetMapping("/weather/search")
//    @HystrixCommand(fallbackMethod = "getDetailsFallback")
//    public CompletableFuture<Map<String, Object>> getDetails() {
//
//        CompletableFuture<String> studentTeacherFuture = CompletableFuture.supplyAsync(() -> {
//            return restTemplate.getForObject("http://student/student", String.class);
//        });
//
//        CompletableFuture<String> detailsFuture = CompletableFuture.supplyAsync(() -> {
//            return restTemplate.getForObject("http://details/details/port", String.class);
//        });
//
//        return studentTeacherFuture
//                .thenCombine(detailsFuture, (studentTeacher, details) -> {
//                    Map<String, Object> result = new HashMap<>();
//                    result.put("student", studentTeacher);
//                    result.put("details", details);
//                    return result;
//                })
//                .exceptionally(ex -> {
//                    return getDetailsFallback();
//                });
//    }

    // Fallback method for getDetails
//    public CompletableFuture<Map<String, Object>> getDetailsFallback() {
//        Map<String, Object> fallbackResult = new HashMap<>();
//        fallbackResult.put("student", "Fallback student data");
//        fallbackResult.put("details", "Fallback details data");
//        return CompletableFuture.completedFuture(fallbackResult);
//    }

    @GetMapping("/weather/search")
    @HystrixCommand(fallbackMethod = "getDetailsFallback")
    public Map<String, Object> getDetails() {
        try {
//            Thread.sleep(3000);
            String studentTeacher = restTemplate.getForObject("http://student/student", String.class);

            String details = restTemplate.getForObject("http://details/details/port", String.class);

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
