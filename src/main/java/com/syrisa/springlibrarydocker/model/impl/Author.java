package com.syrisa.springlibrarydocker.model.impl;

import com.syrisa.springlibrarydocker.dto.AuthorDto;
import com.syrisa.springlibrarydocker.model.Model;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Author implements Model {
    @Id
    private int authorId;

    private String authorName;

    public AuthorDto toAuthorDto() {
        return AuthorDto.builder()
                .authorId(this.authorId)
                .authorName(this.authorName)
                .build();
    }
}
