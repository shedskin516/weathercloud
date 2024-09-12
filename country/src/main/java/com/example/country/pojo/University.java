package com.example.country.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data // generate getter、setter、toString()、equals()、hashCode() and requiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class University {

    private List<String> domains;
    @JsonProperty("state-province")
    private String state_province;
    private String country;
    private List<String> web_pages;
    private String name;
    private String alpha_two_code;

}
