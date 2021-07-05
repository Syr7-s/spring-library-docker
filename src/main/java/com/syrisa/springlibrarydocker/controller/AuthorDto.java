package com.syrisa.springlibrarydocker.controller;

import com.syrisa.springlibrarydocker.model.impl.Author;
import com.syrisa.springlibrarydocker.model.impl.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto {

    private long authorId;

    private String authorName;

    private List<Book> registeredAuthorBook;

    public Author toAuthor() {
        return Author.builder()
                .authorId(this.authorId)
                .authorName(this.authorName)
                .registeredAuthorBook(this.registeredAuthorBook)
                .build();
    }
}
