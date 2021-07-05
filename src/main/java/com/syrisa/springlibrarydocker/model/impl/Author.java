package com.syrisa.springlibrarydocker.model.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.syrisa.springlibrarydocker.controller.AuthorDto;
import com.syrisa.springlibrarydocker.model.Model;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Author implements Model {
    @Id
    private long authorId;

    private String authorName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "author_book",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_isbn"))
    @JsonIgnore
    private List<Book> registeredAuthorBook;

    public AuthorDto toAuthorDto() {
        return AuthorDto.builder()
                .authorId(this.authorId)
                .authorName(this.authorName)
                .registeredAuthorBook(this.registeredAuthorBook)
                .build();
    }
}
