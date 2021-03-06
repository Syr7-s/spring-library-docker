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
        try {
            author.setAuthorId(AuthorId.numberGenerate.generateNumber(AUTHOR_ID_LENGTH));
            return authorRepository.save(author);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @Override
    public Author update(Author author) {
        if (getAuthorById(author.getAuthorId()) != null) {
            return authorRepository.save(author);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not updated.");
        }

    }

    @Override
    public Page<Author> getAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Author getByAuthorName(String authorName) {
        try {
            return authorRepository.findAuthorByAuthorName(authorName);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Author could not found by authorByName");
        }
    }

    @Override
    public Author getAuthorById(long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author could not found."));
    }

    @Override
    public String delete(long authorId) {
        try {
            Author author = getAuthorById(authorId);
            authorRepository.delete(author);
            return authorId + " named author was deleted.";
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Author not deleted.");
        }
    }

}
