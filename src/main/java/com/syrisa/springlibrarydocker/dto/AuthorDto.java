package com.syrisa.springlibrarydocker.dto;

import com.syrisa.springlibrarydocker.model.impl.Author;
import com.syrisa.springlibrarydocker.model.impl.Book;
import lombok.Builder;
import lombok.Data;



import java.util.List;
@Data
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
