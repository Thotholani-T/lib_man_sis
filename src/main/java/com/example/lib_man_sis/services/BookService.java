package com.example.lib_man_sis.services;

import com.example.lib_man_sis.models.Book;
import com.example.lib_man_sis.repos.BookRepository;
import com.example.lib_man_sis.repos.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    //Add new book to database
    public void save(Book book) {
        bookRepository.save(book);
    }

    //get book by id
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public void update(Book book) {
        bookRepository.save(book);
    }
}