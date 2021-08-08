package com.syrisa.springlibrarydocker.controller;

import com.syrisa.springlibrarydocker.dto.OrdersDto;
import com.syrisa.springlibrarydocker.model.impl.*;
import com.syrisa.springlibrarydocker.utility.gender.Gender;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

class OrderControllerTest {
    static RestTemplate restTemplate;
    private static final OrdersDto ORDERS_DTO = new OrdersDto();
    private static final String URI_BOOK = "http://localhost:8080/api/v1/book";
    private static final String URI_CATEGORY = "http://localhost:8080/api/v1/category";
    private static final String URI_AUTHOR = "http://localhost:8080/api/v1/author";
    private static final String URI_USER = "http://localhost:8080/api/v1/user";
    private static final String URI_ORDER = "http://localhost:8080/api/v1/order";
    private static OrdersDto editedOrder;
    private static Book editedBook;
    private static Category editedCategory;
    private static User editedUser;

    @BeforeAll
    static void init() {
        restTemplate = new RestTemplate();
        ORDERS_DTO.setCreatedAt(LocalDate.of(2021, 7, 7));
        ORDERS_DTO.setRegisteredOrderBook(book());
        ORDERS_DTO.setUser(createUser());
        ORDERS_DTO.setTotal(25);
        URI uri = restTemplate.postForLocation(URI_ORDER, ORDERS_DTO);
        assert uri != null;
        editedOrder = restTemplate.getForObject(URI_ORDER, OrdersDto.class);

    }

    @Test
    void checkTotal() {
        Assertions.assertTrue(editedOrder.getTotal() > 0);
    }

    @AfterAll
    static void delete() {
        assert editedOrder != null;
        restTemplate.delete(URI_ORDER + "/undo/" + editedOrder.getId(), ORDERS_DTO);
        restTemplate.delete(URI_BOOK + "/undo/" + editedBook.getBookIsbnNO(), editedBook);
        restTemplate.delete(URI_CATEGORY + "/delete/" + editedCategory.getCategoryId(), editedCategory);
        restTemplate.delete(URI_USER + "/undo/" + editedUser.getUserID(), editedUser);
    }

    private static User createUser() {
        User user = new User();
        user.setUserName("John");
        user.setUserLastName("WICK");
        user.setUserGender(Gender.MALE);
        user.setUserPhone("905306952824");
        user.setUserEmail("jwick@gmail.com");
        user.setUserBirthDate(LocalDate.of(1970, 1, 1));
        user.setAddress(createAddress());
        URI uri = restTemplate.postForLocation(URI_USER, user);
        assert uri != null;
        editedUser = restTemplate.getForObject(uri, User.class);
        return editedUser;
    }

    private static Address createAddress() {
        Address address = new Address();
        address.setCity("LONDON");
        address.setStreet("LONDON");
        address.setNumber("150");
        address.setZipcode(650452);
        return address;
    }

    private static List<Book> book() {
        Book book = new Book();
        book.setBookName("TheGrapesOfWrath");
        book.setBookDescription("writer by John Steinbeck");
        book.setBookPublishedDate(LocalDate.of(1940, 1, 1));
        book.setBookAddedDate(LocalDate.of(2015, 1, 1));
        book.setBookPrice(25);
        book.setCurrency("USD");
        book.setImageUrl("theGrapesOfWrath.png");
        book.setCategory(createCategory());
        book.setAuthors(authorCreate());
        URI uriBook = restTemplate.postForLocation(URI_BOOK, book);
        assert uriBook != null;
        editedBook = restTemplate.getForObject(uriBook, Book.class);
        List<Book> books = new ArrayList<>();
        books.add(editedBook);
        return books;
    }

    private static Category createCategory() {
        Category category = new Category();
        category.setCategoryName("Story");
        URI uriCategory = restTemplate.postForLocation(URI_CATEGORY, category);
        assert uriCategory != null;
        editedCategory = restTemplate.getForObject(uriCategory, Category.class);
        return editedCategory;
    }

    private static List<Author> authorCreate() {
        Author author = new Author();
        author.setAuthorName("JohnSteinbeck");
        URI uriAuthor = restTemplate.postForLocation(URI_AUTHOR, author);
        List<Author> authors = new ArrayList<>();
        assert uriAuthor != null;
        Author editedAuthor = restTemplate.getForObject(uriAuthor, Author.class);
        assert editedAuthor != null;
        authors.add(editedAuthor);
        return authors;
    }
}