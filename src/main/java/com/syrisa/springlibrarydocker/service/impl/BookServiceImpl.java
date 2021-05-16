package com.syrisa.springlibrarydocker.service.impl;

import com.syrisa.springlibrarydocker.model.impl.Author;
import com.syrisa.springlibrarydocker.model.impl.Book;
import com.syrisa.springlibrarydocker.model.impl.Category;
import com.syrisa.springlibrarydocker.repository.BookRepository;
import com.syrisa.springlibrarydocker.service.AuthorService;
import com.syrisa.springlibrarydocker.service.BookService;
import com.syrisa.springlibrarydocker.service.CategoryService;
import com.syrisa.springlibrarydocker.service.CoreLibrary;
import com.syrisa.springlibrarydocker.utility.generate.isbnnumber.ISBN;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Transactional
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private AuthorService authorService;
    private final List<Book> books = new ArrayList<>();

    public BookServiceImpl(BookRepository bookRepository, CategoryService categoryService, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    @Override
    public Book create(Book book) {
        try {
            String isbn = ISBN.generateISBN.get();
            book.setBookIsbnNO(ISBN.cleanNotNumber.apply(isbn));
            book.setBookIsbn(isbn);
            Category category = categoryService.findCategoryByCategoryName(book.getCategory().getCategoryName());
            book.setCategory(category);
            List<String> bookAuthors = book.getAuthors().stream().map(Author::getAuthorName).collect(Collectors.toList());
            // List<Author> authors = authorCreate.apply(bookAuthors);
            List<Author> authors = writerBook.apply(bookAuthors);
            book.setAuthors(authors);

          /*  Author author = new Author();
            author.setRegisteredAuthorBook((List<Book>) book);
            authorService.update()*/
            Book newBook=bookRepository.save(book);
            coreLibrary.regBook(authors, newBook);
            return newBook;
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    CoreLibrary<List<Author>, Book> coreLibrary = (authors, book) -> {
        books.add(book);
        for (Author author : authors) {
            author.setRegisteredAuthorBook(books);
            authorService.update(author);
        }
    };

    /*
        private final Function<List<String>, List<Author>> authorCreate = author -> {
            List<Author> authors = new ArrayList<>();
            for (String name : author) {
                Author authorN = new Author();
                //authorN.setAuthorId(CredentialNumber.generateNumber.get());
                authorN.setAuthorName(name);
                authors.add(authorService.create(authorN));
            }
            return getAuthors(author, authors);
        };
    */
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

    private final Function<List<String>, List<Author>> writerBook = authorName -> {
        List<Author> authors = new ArrayList<>();
        for (String name : authorName) {
            try {
                Author author = authorService.getByAuthorName(name);
                if (author != null) {
                    authors.add(author);
                }
            } catch (Exception exception) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, name + " named writer was not found");
            }
        }
        return getAuthors(authorName, authors);
    };

    private List<Author> getAuthors(List<String> authorName, List<Author> authors) {
        if (authors.size() == authorName.size()) {
            return authors;
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Author not registered on the system.");
        }
    }

}
