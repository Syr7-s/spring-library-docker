package com.syrisa.springlibrarydocker.service;

import com.syrisa.springlibrarydocker.model.impl.Author;

import java.util.List;

public interface AuthorService extends ModelService<Author> {

    int authorIdLength = 10;

    Author getByAuthorName(String authorName);

    Author getAuthorById(long authorId);

    String delete(long authorId);
}
