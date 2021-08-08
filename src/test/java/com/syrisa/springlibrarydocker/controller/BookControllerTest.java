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
    private static final String REQUEST_URI_BOOK = "http://localhost:8080/api/v1/book";
    private static final String REQUEST_URI_CATEGORY = "http://localhost:8080/api/v1/category";
    private static final String REQUEST_URI_AUTHOR = "http://localhost:8080/api/v1/author";
    private static BookDto editedBook;
    private static Category editedCategory;

    @BeforeAll
    static void init() {
        restTemplate = new RestTemplate();
        BOOK_DTO.setBookName("TheGrapesOfWrath");
        BOOK_DTO.setBookDescription("writer by John Steinbeck");
        BOOK_DTO.setBookPublishedDate(LocalDate.of(1940, 1, 1));
        BOOK_DTO.setBookAddedDate(LocalDate.of(2015, 1, 1));
        BOOK_DTO.setBookPrice(25);
        BOOK_DTO.setCurrency("USD");
        BOOK_DTO.setImageUrl("theGrapesOfWrath.png");
        BOOK_DTO.setCategory(createCategory());
        BOOK_DTO.setAuthors(authorCreate());
        BOOK_DTO.setOrders(null);

        URI uriBook = restTemplate.postForLocation(REQUEST_URI_BOOK, BOOK_DTO);
        assert uriBook != null;
        editedBook = restTemplate.getForObject(uriBook, BookDto.class);
    }


    @Test
    @Order(1)
    void checkPrice() {
        assert editedBook != null;
        Assertions.assertEquals(25, editedBook.getBookPrice());
    }

    @Test
    @Order(2)
    void delete() {
        assert editedBook != null;
        restTemplate.delete(REQUEST_URI_BOOK + "/undo/" + editedBook.getBookIsbnNO(), BookDto.class);
        Assertions.assertTrue(true);
    }

    @AfterAll
    static void deleteCategory() {
        assert editedCategory != null;
        restTemplate.delete(REQUEST_URI_CATEGORY + "/delete/" + editedCategory.getCategoryId(), editedCategory);
    }

    private static Category createCategory() {
        Category category = new Category();
        category.setCategoryName("Story");
        URI uriCategory = restTemplate.postForLocation(REQUEST_URI_CATEGORY, category);
        assert uriCategory != null;
        editedCategory = restTemplate.getForObject(uriCategory, Category.class);
        return editedCategory;
    }

    private static List<Author> authorCreate() {
        Author author = new Author();
        author.setAuthorName("JohnSteinbeck");
        URI uriAuthor = restTemplate.postForLocation(REQUEST_URI_AUTHOR, author);
        List<Author> authors = new ArrayList<>();
        assert uriAuthor != null;
        Author editedAuthor = restTemplate.getForObject(uriAuthor, Author.class);
        assert editedAuthor != null;
        authors.add(editedAuthor);
        return authors;
    }
}