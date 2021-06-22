package com.syrisa.springlibrarydocker.service.impl;

import com.syrisa.springlibrarydocker.model.impl.Author;
import com.syrisa.springlibrarydocker.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AuthorServiceImplTest {
    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    AuthorServiceImpl authorService;

    private static Map<Long, Author> authors = new HashMap<>();

    @BeforeAll
    static void createAuthor() {
        addAuthor(1L, "Fyodor Dostoyevski");
        addAuthor(2L, "Lev Tolstoy");
        addAuthor(3L, "Goethe");
        addAuthor(4L, "Charlotte Bronte");
    }

    @BeforeEach
    void setMockOutput() {
        authorService = new AuthorServiceImpl(authorRepository);
        Set<Long> authorSet = authors.keySet();
        for (Long id : authorSet) {
            authorRepository.save(authors.get(id));
        }
    }

    @Test
    void getAuthorName() {
        Mockito.when(authorRepository.findAuthorByAuthorName(authors.get(1L).getAuthorName())).thenReturn(authors.get(1L));
        Assertions.assertEquals("Fyodor Dostoyevski", authorService.getByAuthorName(authors.get(1L).getAuthorName()).getAuthorName());
    }

    private static void addAuthor(long id, String name) {
        Author author = new Author();
        author.setAuthorId(id);
        author.setAuthorName(name);
        author.setRegisteredAuthorBook(null);
        authors.put(author.getAuthorId(), author);
    }

}