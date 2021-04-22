package com.syrisa.springlibrarydocker.repository;

import com.syrisa.springlibrarydocker.model.impl.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {
    Author findAuthorByAuthorName(String authorName);

    Page<Author> findAll(Pageable pageable);
}
