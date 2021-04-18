package com.syrisa.springlibrarydocker.dto;

import com.syrisa.springlibrarydocker.model.impl.Author;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDto {

    private int authorId;

    private String authorName;

    public Author toAuthor() {
        return Author.builder()
                .authorId(this.authorId)
                .authorName(this.authorName)
                .build();
    }
}
