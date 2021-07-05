package com.syrisa.springlibrarydocker.controller;

import com.syrisa.springlibrarydocker.dto.CategoryDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


class CategoryControllerTest {
    static RestTemplate restTemplate;
    private static final CategoryDto CATEGORY_DTO = new CategoryDto();
    private static final String REQUEST_URI = "http://localhost:8080/api/v1/category";
    private static URI uri;
    private static CategoryDto editedCategory;
    @BeforeAll
    static void init(){
        restTemplate = new RestTemplate();
        CATEGORY_DTO.setCategoryName("History");

        uri = restTemplate.postForLocation(REQUEST_URI,CATEGORY_DTO);
        assert uri != null;
        editedCategory = restTemplate.getForObject(uri,CategoryDto.class);
    }

    @Test
    @Order(1)
    void save(){
        assert uri != null ;
        assert editedCategory !=null;
        Assertions.assertEquals("History",editedCategory.getCategoryName());
    }

    @Test
    @Order(2)
    void delete(){
        assert editedCategory != null;
        restTemplate.delete(REQUEST_URI+"/delete/"+editedCategory.getCategoryId(),CATEGORY_DTO);
    }
}