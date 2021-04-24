package com.syrisa.springlibrarydocker.repository;

import com.syrisa.springlibrarydocker.model.impl.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findAuthorByAuthorName(String authorName);

    Page<Author> findAll(Pageable pageable);
}
