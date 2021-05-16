package com.syrisa.springlibrarydocker.service.impl;


import com.syrisa.springlibrarydocker.model.impl.Book;
import com.syrisa.springlibrarydocker.model.impl.Orders;
import com.syrisa.springlibrarydocker.model.impl.User;
import com.syrisa.springlibrarydocker.repository.OrderRepository;
import com.syrisa.springlibrarydocker.service.BookService;
import com.syrisa.springlibrarydocker.service.OrderService;
import com.syrisa.springlibrarydocker.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private BookService bookService;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, BookService bookService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    @Override
    public Orders create(Orders orders) {
        try {
            User user = userService.getById(orders.getUser().getUserID());
            List<Long> booksISBNs = orders.getRegisteredOrderBook().stream().map(Book::getBookIsbnNO).collect(Collectors.toList());
            List<Book> registerBook = bookOnSet.apply(booksISBNs);
            orders.setUser(user);
            orders.setRegisteredOrderBook(registerBook);
            return orderRepository.save(orders);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }


    @Override
    public Orders update(Orders orders) {
        if (getOrdersByOrdersId(orders.getId()) != null) {
            return orderRepository.save(orders);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, orders.getId() + " named orders not found");
        }
    }

    @Override
    public Page<Orders> getAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Orders getOrdersByOrdersId(int id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id + " numbered book was not found."));
    }

    @Override
    public String delete(int ordersId) {
        Orders order = getOrdersByOrdersId(ordersId);
        orderRepository.delete(order);
        return ordersId + " numbered order was deleted.";
    }

    private final Function<List<Long>, List<Book>> bookOnSet = bookIsbn -> {
        List<Book> books = new ArrayList<>();
        Book book;
        for (Long isbn : bookIsbn) {
            try {
                book = bookService.getBookByBookIsbn(isbn);
                if (book != null) {
                    books.add(book);
                }
            } catch (Exception exception) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, isbn + " numbered book not found.");
            }
        }
        return getBooks(bookIsbn, books);
    };

    private List<Book> getBooks(List<Long> bookIsbn, List<Book> books) {
        if (books.size() == bookIsbn.size()) {
            return books;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found on the library or the isbn number is false.");
        }
    }
}
