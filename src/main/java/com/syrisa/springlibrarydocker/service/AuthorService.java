package com.syrisa.springlibrarydocker.service;

import com.syrisa.springlibrarydocker.model.impl.Author;

public interface AuthorService extends ModelService<Author> {
    Author getByAuthorName(String authorName);

    Author getAuthorById(long authorId);

    String delete(int authorId);
}
