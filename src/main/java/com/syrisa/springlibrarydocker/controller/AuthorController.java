package com.syrisa.springlibrarydocker.controller;

import com.syrisa.springlibrarydocker.dto.AuthorDto;
import com.syrisa.springlibrarydocker.model.impl.Author;
import com.syrisa.springlibrarydocker.service.AuthorService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDto create(@RequestBody AuthorDto authorDto) {
        try {
            return authorService.create(authorDto.toAuthor()).toAuthorDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @PutMapping("/update")
    public AuthorDto update(@RequestBody AuthorDto authorDto) {
        try {
            return authorService.update(authorDto.toAuthor()).toAuthorDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @GetMapping(value = "/authors", params = {"page", "size"})
    public List<AuthorDto> getAll(@Min(value = 0) int page, @Min(value = 1) int size) {
        return authorService.getAll(PageRequest.of(page, size))
                .stream()
                .map(Author::toAuthorDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{authorName}/author")
    public AuthorDto getAuthorByAuthorName(@PathVariable("authorName") String authorName) {
        try {
            return authorService.getByAuthorName(authorName).toAuthorDto();
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage());
        }
    }

    @GetMapping("/author/{authorId}")
    public AuthorDto getAuthorById(@PathVariable("authorId") long authorId) {
        try {
            return authorService.getAuthorById(authorId).toAuthorDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{authorId}")
    public String delete(@PathVariable("authorId") long authorId) {
        try {
            return authorService.delete(authorId);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }
}
