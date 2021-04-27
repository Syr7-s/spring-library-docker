package com.syrisa.springlibrarydocker.repository;


import com.syrisa.springlibrarydocker.model.impl.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Book findBookByBookName(String bookName);

    Page<Book> findAll(Pageable pageable);
}
