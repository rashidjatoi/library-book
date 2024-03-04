package com.kristal.librarybook.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kristal.librarybook.domain.BookEntity;
import com.kristal.librarybook.services.impl.BookServiceImpl;

@RestController
@RequestMapping(path = "/book")
public class BookController {

    private final BookServiceImpl bookService;

    // Constructor injection of BookServiceImpl
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    // Endpoint to create a new book
    @PostMapping(path = "/save")
    public ResponseEntity<BookEntity> createBook(@RequestBody BookEntity book) {
        bookService.createBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // Endpoint to get all books
    @GetMapping
    public ResponseEntity<List<BookEntity>> getAllBooks() {
        List<BookEntity> allBook = bookService.getAllBook();
        if (allBook.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(bookService.getAllBook(), HttpStatus.OK);
    }

    // Endpoint to get a book by its ISBN
    @GetMapping("/{isbn}")
    public ResponseEntity<BookEntity> getBookById(@PathVariable String isbn) {
        Optional<BookEntity> foundBook = bookService.getBookById(isbn);
        if (foundBook.isPresent()) {
            return new ResponseEntity<>(foundBook.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to update a book by its ISBN
    @PutMapping("/{isbn}")
    public ResponseEntity<?> updateBook(@PathVariable String isbn, @RequestBody BookEntity updatedBook) {
        if (isbn.isEmpty()) {
            return new ResponseEntity<>("ISBN cannot be empty", HttpStatus.BAD_REQUEST);
        }

        if (updatedBook == null) {
            return new ResponseEntity<>("Updated book information is missing", HttpStatus.BAD_REQUEST);
        }

        if (!isbn.equals(updatedBook.getIsbn())) {
            return new ResponseEntity<>("ISBN in URL and updated book do not match", HttpStatus.BAD_REQUEST);
        }

        bookService.updateBook(isbn, updatedBook);
        return new ResponseEntity<>("Book updated successfully", HttpStatus.OK);
    }

    // Endpoint to delete a book by its ISBN
    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> deleteBookById(@PathVariable String isbn) {
        if (!isbn.isEmpty()) {
            bookService.deleteBookById(isbn);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
