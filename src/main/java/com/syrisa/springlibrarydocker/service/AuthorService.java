package com.syrisa.springlibrarydocker.service;

import com.syrisa.springlibrarydocker.model.impl.Author;


public interface AuthorService extends ModelService<Author> {

    int AUTHOR_ID_LENGTH = 10;

    Author getByAuthorName(String authorName);

    Author getAuthorById(long authorId);

    String delete(long authorId);
}
