package com.syrisa.springlibrarydocker.controller;


import com.syrisa.springlibrarydocker.dto.BookDto;
import com.syrisa.springlibrarydocker.model.impl.Author;
import com.syrisa.springlibrarydocker.model.impl.Category;
import org.junit.jupiter.api.*;

import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class BookControllerTest {
    static RestTemplate restTemplate;
    private static final BookDto BOOK_DTO = new BookDto();
    private static URI uriBook;
    private static final String REQUEST_URI_BOOK = "http://localhost:8080/api/v1/book";
    private static final String REQUEST_URI_CATEGORY = "http://localhost:8080/api/v1/category";
    private static final String REQUEST_URI_AUTHOR = "http://localhost:8080/api/v1/author";
    private static BookDto editedBook;

    @BeforeAll
    static void init() {
        restTemplate = new RestTemplate();
        BOOK_DTO.setBookName("TheGrapesofWrath");
        BOOK_DTO.setBookDescription("writer by John Steinbeck");
        BOOK_DTO.setBookPublishedDate(LocalDate.of(1940, 1, 1));
        BOOK_DTO.setBookAddedDate(LocalDate.of(2015, 1, 1));
        BOOK_DTO.setBookPrice(25);
        BOOK_DTO.setCurrency("USD");
        BOOK_DTO.setImageUrl("theGrapesOfWrath.png");
        BOOK_DTO.setCategory(createCategory());
        BOOK_DTO.setAuthors(authorCreate());
        BOOK_DTO.setOrders(null);

        uriBook = restTemplate.postForLocation(REQUEST_URI_BOOK, BOOK_DTO);
        assert uriBook != null;
        editedBook = restTemplate.getForObject(uriBook, BookDto.class);
    }


    @Test
    @Order(1)
    void checkPrice() {
        assert editedBook !=null;
        Assertions.assertTrue( editedBook.getBookPrice() == 25);
    }

   @Test
    @Order(2)
    void delete() {
        assert editedBook != null;
        restTemplate.delete(REQUEST_URI_BOOK + "/undo/" + editedBook.getBookIsbnNO(), BookDto.class);
        Assertions.assertTrue(true);
    }

    public  static Category createCategory() {
        Category category = new Category();
        category.setCategoryName("Roman");
        Category editedCategory = restTemplate.getForObject(REQUEST_URI_CATEGORY+"/Roman", Category.class);
        return editedCategory;
    }

    private static List<Author> authorCreate() {
        List<Author> authors = new ArrayList<>();
        Author editedAuthor = restTemplate.getForObject(REQUEST_URI_AUTHOR+"/John Steinbeck/author", Author.class);
        assert editedAuthor != null;
        authors.add(editedAuthor);
        return authors;
    }
}