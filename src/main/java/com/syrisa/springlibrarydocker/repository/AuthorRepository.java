package com.syrisa.springlibrarydocker.repository;

import com.syrisa.springlibrarydocker.model.impl.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author findAuthorByAuthorName(String authorName);

    Author findAuthorByAuthorId(Long authorId);

    Page<Author> findAll(Pageable pageable);
}
