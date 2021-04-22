package com.syrisa.springlibrarydocker.service.impl;

import com.syrisa.springlibrarydocker.model.impl.Author;
import com.syrisa.springlibrarydocker.repository.AuthorRepository;
import com.syrisa.springlibrarydocker.service.AuthorService;
import com.syrisa.springlibrarydocker.utility.generate.authorid.AuthorId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public Author create(Author author) {
        author.setAuthorId(AuthorId.authorIdGenerate.get());
        return authorRepository.save(author);
    }

    @Override
    public Author update(Author author) {
        return null;
    }

    @Override
    public Page<Author> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Author getByAuthorName(String authorName) {
        return null;
    }

    @Override
    public Author getAuthorById(long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Auhtor could not found."));
    }

    @Override
    public String delete(int authorId) {
        return null;
    }

}
