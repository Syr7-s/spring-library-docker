package com.syrisa.springlibrarydocker.controller;


import com.syrisa.springlibrarydocker.dto.AuthorDto;
import com.syrisa.springlibrarydocker.service.AuthorService;
import com.syrisa.springlibrarydocker.utility.generate.authorid.AuthorId;
import org.junit.jupiter.api.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


class AuthorControllerTest {
    static RestTemplate restTemplate;
    private static final AuthorDto AUTHOR_DTO = new AuthorDto();
    private static final String URI_AUTHOR = "http://localhost:8080/api/v1/author";
    private static AuthorDto authorDto;

    @BeforeAll
    static void init() {
        restTemplate = new RestTemplate();
        AUTHOR_DTO.setAuthorId(AuthorId.numberGenerate.generateNumber(AuthorService.AUTHOR_ID_LENGTH));
        AUTHOR_DTO.setAuthorName("John Steinbeck");
        URI uriAuthor = restTemplate.postForLocation(URI_AUTHOR, AUTHOR_DTO);
        assert uriAuthor != null;
        authorDto = restTemplate.getForObject(uriAuthor, AuthorDto.class);
    }

    @Test
    @Order(1)
    void checkAuthorNameLength() {
        assert authorDto != null;
        Assertions.assertTrue(authorDto.getAuthorName().length() > 10);
    }

    @AfterAll
    static void delete() {
        assert authorDto != null;
        restTemplate.delete(URI_AUTHOR + "/delete/" + authorDto.getAuthorId());
    }
}