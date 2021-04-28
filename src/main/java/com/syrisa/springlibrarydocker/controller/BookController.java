package com.syrisa.springlibrarydocker.controller;

import com.syrisa.springlibrarydocker.dto.BookDto;
import com.syrisa.springlibrarydocker.model.impl.Book;
import com.syrisa.springlibrarydocker.service.BookService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto create(@RequestBody BookDto bookDto) {
        try {
            return bookService.create(bookDto.toBook()).toBookDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @PutMapping
    public BookDto update(@RequestBody BookDto bookDto) {
        try {
            return bookService.update(bookDto.toBook()).toBookDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @GetMapping(value = "/books", params = {"page", "size"})
    public List<BookDto> getBooks(@Min(0) int page, @Min(1) int size) {
        return bookService.getAll(PageRequest.of(page, size))
                .stream()
                .map(Book::toBookDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{bookName}")
    public Book getBookByName(@PathVariable("bookName") String bookName) {
        try {
            return bookService.getBookByBookName(bookName);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/isbn/{bookIsbn}")
    public Book getBookByBookIsbn(@PathVariable("bookIsbn") long bookIsbn) {
        try {
            return bookService.getBookByBookIsbn(bookIsbn);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @DeleteMapping("/undo/{bookIsbn}")
    public String delete(@PathVariable("bookIsbn") long bookIsbn) {
        try {
            return bookService.delete(bookIsbn);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }
}
