package com.syrisa.springlibrarydocker.service.impl;

import com.syrisa.springlibrarydocker.model.impl.Book;
import com.syrisa.springlibrarydocker.model.impl.Category;
import com.syrisa.springlibrarydocker.repository.BookRepository;
import com.syrisa.springlibrarydocker.service.BookService;
import com.syrisa.springlibrarydocker.service.CategoryService;
import com.syrisa.springlibrarydocker.utility.generate.isbnnumber.ISBN;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Transactional
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Book create(Book book) {
        try {
            String isbn = ISBN.generateISBN.get();
            book.setBookIsbnNO(ISBN.cleanNotNumber.apply(isbn));
            book.setBookIsbn(isbn);
            Category category = categoryService.findCategoryByCategoryName(book.getCategory().getCategoryName());
            book.setCategory(category);
            return bookRepository.save(book);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }

    }

    @Override
    public Book update(Book book) {
        try {
            if (getBookByBookIsbn(book.getBookIsbnNO()) != null) {
                return bookRepository.save(book);
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error an occurred.");
            }
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @Override
    public Page<Book> getAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book getBookByBookName(String bookName) {
        try {
            return bookRepository.findBookByBookName(bookName);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, bookName + " named book was not found.");
        }
    }

    @Override
    public Book getBookByBookIsbn(long bookIsbn) {
        return bookRepository.findById(bookIsbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, bookIsbn + " numbered was book not found."));
    }

    @Override
    public String delete(long bookIsbn) {
        try {
            Book book = getBookByBookIsbn(bookIsbn);
            if (book != null) {
                bookRepository.delete(book);
                return bookIsbn + " number book was deleted.";
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, bookIsbn + " number book was deleted.");
            }
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }
}
