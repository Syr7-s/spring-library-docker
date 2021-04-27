package com.syrisa.springlibrarydocker.service;

import com.syrisa.springlibrarydocker.model.impl.Book;

public interface BookService extends ModelService<Book> {
    Book getBookByBookName(String bookName);

    String delete(long bookIsbn);
}
