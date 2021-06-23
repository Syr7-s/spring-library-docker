package com.syrisa.springlibrarydocker.service.impl;

import com.syrisa.springlibrarydocker.model.impl.Book;
import com.syrisa.springlibrarydocker.repository.BookRepository;
import com.syrisa.springlibrarydocker.utility.generate.isbnnumber.ISBN;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BookServiceImplTest{
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookService;

    private static final Map<String, Book> bookMap = new HashMap<>();

    @BeforeAll
    static void createBook(){
        addBook("Crime and Punishment",
                "Crime and Punishment is a novel who write by Dostoyevski.",
                LocalDate.of(1866,1,1),
                LocalDate.of(2010,1,1),
                25.0,
                "USD",
                "crimeandpunishment.png");

        addBook("The Museum of Innocence",
                "The Museum of Innocence is a novel who write by Orhan PAMUK.",
                LocalDate.of(2008,8,29),
                LocalDate.of(2015,1,1),
                45.5,
                "TRY",
                "museumofinnocence.png");

        addBook("Miserables",
                "Miserables is a novel who write by Victo Hugo",
                LocalDate.of(1862,1,1),
                LocalDate.of(2000,2,5),
                60.99,
                "EUR",
                "miserables.png");
    }

    @BeforeEach
    void setMockOutput(){
        bookService = new BookServiceImpl(bookRepository,null,null);
        Set<String> bookSet = bookMap.keySet();
        for (String bookName:bookSet) {
            bookRepository.save(bookMap.get(bookName));
        }
    }
    @Test
    void getPriceGreaterThanZero(){
        Mockito.when(bookRepository.findBookByBookName("Miserables")).thenReturn(bookMap.get("Miserables"));
        Assertions.assertTrue(bookService.getBookByBookName("Miserables").getBookPrice()>0);
    }
    private static void addBook(String bookName, String bookDescription, LocalDate publishedDate,LocalDate addedDate,Double price,String currency,String imageUrl){
        Book book = new Book();

        String isbn = ISBN.generateISBN.get();

        book.setBookIsbnNO(ISBN.cleanNotNumber.apply(isbn));
        book.setBookIsbn(isbn);
        book.setBookName(bookName);
        book.setBookDescription(bookDescription);
        book.setBookPublishedDate(publishedDate);
        book.setBookAddedDate(addedDate);
        book.setBookPrice(price);
        book.setCurrency(currency);
        book.setImageUrl(imageUrl);
        book.setCategory(null);
        book.setAuthors(null);
        book.setOrders(null);

        bookMap.put(book.getBookName(), book);
    }
}